package team.gtfm.server.db;

/**
 * 
 * @author kwSeo
 *
 */
public class MappingSessionFactoryBean {
	private MappingSessionFactory factory;
	
	public MappingSessionFactoryBean(){
		this.factory = MappingSessionFactory.get();
	}
	
	public MappingSessionFactory getMappingSessionFactory(){
		return factory;
	}
	
}
