package team.gtfm.server.db;


import org.springframework.stereotype.Repository;

/**
 * 
 * @author kwSeo
 *
 */
@Repository
public class ChickenDao {

	
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
