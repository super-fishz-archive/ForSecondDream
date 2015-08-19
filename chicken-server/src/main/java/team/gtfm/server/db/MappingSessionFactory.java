package team.gtfm.server.db;

/**
 * 
 * @author kwSeo
 *
 */
public class MappingSessionFactory {
	private static final MappingSessionFactory factory = new MappingSessionFactory();
	
	private MappingSessionFactory(){}
	
	public static MappingSessionFactory get(){
		return factory;
	}
	
	public MappingSession openSession(){
		return new MappingSessionImpl(DBManager.newConnection());
	}
}
