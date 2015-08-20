package team.gtfm.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * 
 * @author kwSeo
 *
 */
public class MappingSessionImpl implements MappingSession {
	private Connection conn;
	
	public MappingSessionImpl(Connection conn){
		this.conn = conn;
		
	}
	
	@Override
	public <T> T selectOne(String sql, Class<T> classType) {
		List<T> instanceList = selectList(sql, classType);
		
		if(instanceList.isEmpty())
			return null;
		
		return instanceList.get(0);
	}
	

	@Override
	public <T> T selectOne(PreparedStatement pstmt, Class<T> classType) {
		List<T> instanceList = selectList(pstmt, classType);
		
		if(instanceList.isEmpty())
			return null;
		
		return instanceList.get(0);
	}

	@Override
	public <T> List<T> selectList(String sql, Class<T> classType) {
			PreparedStatement pstmt = parepareStatement(sql);
			return selectList(pstmt, classType);
	}
	
	@Override
	public void close() {
		try{
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace(System.err);
			throw new RuntimeException("SQL Connection close exception. " + e.getMessage());
		}
	}


	@Override
	public <T> List<T> selectList(PreparedStatement pstmt, Class<T> classType) {
		try{
			ResultSet resultSet = pstmt.executeQuery();
			
			ResultSetMapper mapper = new ResultSetMapper();
			
			return mapper.readAsList(resultSet, classType);
			
		}catch(Exception e){
			e.printStackTrace(System.err);
			throw new RuntimeException("Error in MappingSessionImpl.selectList");
		}
	}

	@Override
	public Connection getConnection() {
		return this.conn;
	}

	@Override
	public PreparedStatement parepareStatement(String sql) {
		try{
			return conn.prepareStatement(sql);
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new RuntimeException("Error preparing statement. " + e.getMessage());
		}
	}
	
	
	
}
