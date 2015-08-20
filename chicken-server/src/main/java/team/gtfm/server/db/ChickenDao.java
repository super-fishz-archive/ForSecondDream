package team.gtfm.server.db;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team.gtfm.server.bean.Chicken;
import team.gtfm.server.bean.LocalChicken;
import team.gtfm.server.bean.SummaryChicken;

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
			String sql = "SELECT * FROM chicken_tb LIMIT " + limit;
			return session.selectList(sql, Chicken.class);
		}
	}
	

	
	public LocalChicken selectLocalChickens(String simpleAddr){
		try(MappingSession session = factoryBean.getMappingSessionFactory().openSession()){ 
			String sql1 = "SELECT row_num, bplc_nm, site_whl_addr, rdn_whl_addr FROM chicken_tb WHERE site_whl_addr LIKE \"" + simpleAddr + "%\"";
			List<SummaryChicken> summaryChickens = session.selectList(sql1, SummaryChicken.class);
			
			String sql2 = "SELECT * FROM chicken_open_close_tb WHERE address LIKE \"" + simpleAddr + "\"";
			LocalChicken localChicken = session.selectOne(sql2, LocalChicken.class);
			localChicken.setSummaryChickens(summaryChickens);
			
			return localChicken;
		}
	}
	
//	public List<LocalChicken> selectLocalChickens(String simpleAddr){
//	try(MappingSession session = factoryBean.getMappingSessionFactory().openSession()){ 
//		String sql = "SELECT c.bplc_nm, c.site_whl_addr site_whl_addr, c.rdn_whl_addr rdn_whl_addr, oc.open_num open_num, oc.close_num close_num FROM chicken_tb c JOIN chicken_open_close_tb oc ON (c.site_whl_addr LIKE \"" + simpleAddr + "%\" AND oc.address = \"" + simpleAddr + "\")";
//		return session.selectList(sql, LocalChicken.class);
//	}
//}
	
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
