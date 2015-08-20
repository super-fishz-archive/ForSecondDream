package team.gtfm.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * 
 * @author kwSeo
 *
 */
public interface MappingSession extends AutoCloseable{
	public <T> T selectOne(String sql, Class<T> classType);
	public <T> T selectOne(PreparedStatement pstmt, Class<T> classType);
	public <T> List<T> selectList(String sql, Class<T> classType);
	public <T> List<T> selectList(PreparedStatement pstmt, Class<T> classType);
	public void close();
	public Connection getConnection();
	public PreparedStatement parepareStatement(String sql);
	
}
