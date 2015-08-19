package team.gtfm.server.db;

import java.util.List;

/**
 * 
 * @author kwSeo
 *
 */
public interface MappingSession extends AutoCloseable{
	public <T> T selectOne(String sql, Class<T> classType);
	public <T> List<T> selectList(String sql, Class<T> classType);
	public void close();
	
}
