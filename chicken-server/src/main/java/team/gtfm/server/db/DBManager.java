package team.gtfm.server.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Spring boot, Mybatis, Hive-jdbc 간의 충돌로 인해 결국 JDBC를 활용한 간단한 매퍼를 만들었다.
 * @author kwSeo
 *
 */
public class DBManager {
	protected static String URL = null;
	protected static String ID = null;
	protected static String PASSWORD = null;
	
	public static void init(String driverName, String url, String id, String password){
		try{
			Class.forName(driverName);
			URL = url;
			ID = id;
			PASSWORD = password;
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
	}

	
	public static Connection newConnection(){
		if(URL == null || ID == null || PASSWORD == null){
			System.err.print("init please");
		}
		
		try{
			return DriverManager.getConnection(URL, ID, PASSWORD);
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new RuntimeException("Error getting Connection. Check init method.");
		}
	}
}
