package team.gtfm.server.db;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	
	public List<String> selectAddress(String firstAddr){
		/*
		 * 경원 매핑 모듈은 원시타입 매핑을 지원하지 않습니다. 
		 */
		try(MappingSession session = factoryBean.getMappingSessionFactory().openSession()){ 
			List<String> addrList = new ArrayList<>();
			
			String sql = "SELECT DISTINCT second_addr FROM address_tb WHERE first_addr = ?";
			PreparedStatement pstmt = session.parepareStatement(sql);
			pstmt.setString(1, firstAddr);
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				addrList.add(resultSet.getString(1));
			}
			
			return addrList;
			
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new RuntimeException("Error in selectAddress(firstAddr). " + e.getMessage());
		}
	}
	
	public List<String> selectAddress(String firstAddr, String secondAddr){
		/*
		 * 경원 매핑 모듈은 원시타입 매핑을 지원하지 않습니다. 
		 */
		try(MappingSession session = factoryBean.getMappingSessionFactory().openSession()){ 
			List<String> addrList = new ArrayList<>();
			
			String sql = "SELECT DISTINCT third_addr FROM address_tb WHERE first_addr = ? AND second_addr = ?";
			PreparedStatement pstmt = session.parepareStatement(sql);
			pstmt.setString(1, firstAddr);
			pstmt.setString(2, secondAddr);
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()){
				addrList.add(resultSet.getString(1));
			}
			
			return addrList;
			
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw new RuntimeException("Error in selectAddress(firstAddr, secondAddr). " + e.getMessage());
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
