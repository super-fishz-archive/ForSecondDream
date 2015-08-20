package team.gtfm.server.db;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team.gtfm.server.bean.Chicken;

/**
 * 
 * @author kwSeo
 *
 */
@Repository
public class ChickenDao {
	
	@Autowired
	private MappingSessionFactoryBean factoryBean;
	
	public List<Chicken> selectChicken(int limit){
		try(MappingSession session = factoryBean.getMappingSessionFactory().openSession()){
			String sql = "SELECT * FROM chicken_tb limit " + limit;
			return session.selectList(sql, Chicken.class);
		}
	}
//	public boolean insertChickenCoord(List<Coord> coordList){
//		try(SqlSession sqlSession = sqlSessionFactoryBean.newSqlSession()){
//			int count = 0;
//			for(Coord coord : coordList){
//				count += sqlSession.insert("team.gtfm.chicken.chickenMapper.insertChickenCoord", coord);
//			}
//			sqlSession.commit();
//			System.out.println("insert: " + count + "/List Size: " + coordList.size());
//			
//			return (count > 0)? true : false;
//		}
//	}
}
