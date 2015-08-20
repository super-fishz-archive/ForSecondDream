package team.gtfm.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import team.gtfm.server.db.DBManager;
import team.gtfm.server.db.MappingSessionFactoryBean;

@Configuration
public class AppConfig {

	@Bean
	public MappingSessionFactoryBean getMapplingSessionFactoryBean(){
		DBManager.init("org.apache.hadoop.hive.jdbc.HiveDriver", 
				"jdbc:hive://localhost:10000/default",
				"",
				"");
		System.out.println("Test Bean");
		return new MappingSessionFactoryBean();
	}
}
