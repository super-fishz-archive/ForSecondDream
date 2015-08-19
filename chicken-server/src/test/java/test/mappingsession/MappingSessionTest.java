package test.mappingsession;

import static org.junit.Assert.*;

import org.junit.Test;

import team.gtfm.server.bean.Chicken;
import team.gtfm.server.db.DBManager;
import team.gtfm.server.db.MappingSession;
import team.gtfm.server.db.MappingSessionFactory;

public class MappingSessionTest {
	@Test
	public void testSession(){
		DBManager.init("org.mariadb.jdbc.Driver", 
				"jdbc:mariadb://localhost:3306/chicken",
				"root",
				"0000");
		
		MappingSessionFactory factory = MappingSessionFactory.get();
		try(MappingSession session = factory.openSession()){
			Chicken chicken = session.selectOne("select * from chicken_tb limit 10", Chicken.class);
			assertNotNull(chicken);
			
			System.out.println(chicken.getBplcNm() + "/" +chicken.getSiteWhlAddr());
		}
		
		
	}
}
