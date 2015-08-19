package team.gtfm.server.db;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import team.gtfm.util.StringUtil;

/**
 * 
 * @author kwSeo
 *
 */
public class ResultSetMapper {
	public <T> List<T> readAsList(ResultSet resultSet, Class<T> classType){
		try{
			List<T> instanceList = new ArrayList<>();
			
			List<String> columnNames = new ArrayList<>();
			int columnCount = resultSet.getMetaData().getColumnCount();
			for(int i=1 ; i<=columnCount ; i++){							//데이터베이스는 1부터 시작함
				columnNames.add(resultSet.getMetaData().getColumnName(i));
			}
			
			while(resultSet.next()){
				T instance = read(resultSet, classType, columnNames);
				instanceList.add(instance);
			}
			
			return instanceList;
			
		}catch(Exception e){
			e.printStackTrace(System.err);
			throw new RuntimeException("Error mapping in ResultSetMapper.readAsList");
		}
	}
	
	//TODO 해야한다.
	public <T> T read(ResultSet resultSet, Class<T> classType, List<String> fieldNames){
		try{
			T instance = classType.newInstance();
			Method[] methods = classType.getMethods();
			
			for(String fieldName : fieldNames){
				Object param = resultSet.getObject(fieldName);
				String name = StringUtil.fromUnderToCamel(fieldName);
				for(Method method : methods){
					if(method.getName().matches("set" + name)){		//setter인지 확인
						method.invoke(instance, param);
						break;
					}
				}
			}
			
			return instance;
			
		}catch(Exception e){
			e.printStackTrace(System.err);
			throw new RuntimeException("Error mapping in ResultSetMapper.read");
		}
	}
}
