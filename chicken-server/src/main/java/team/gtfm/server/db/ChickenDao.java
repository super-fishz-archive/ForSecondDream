package team.gtfm.server.db;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team.gtfm.server.bean.Chicken;
import team.gtfm.server.bean.Coord;
import team.gtfm.server.bean.SummaryChicken;

@Repository
public class ChickenDao {
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactoryBean;
	
	public List<Chicken> selectChicken(int limit){
		return selectChicken(0, limit);
	}
	
	public List<Chicken> selectChicken(int start, int limit){
		try(SqlSession sqlSession = sqlSessionFactoryBean.newSqlSession()){
			Map<String, Object> param = new HashMap<>();
			param.put("start", start);
			param.put("limit", limit);
			return sqlSession.selectList("team.gtfm.chicken.chickenMapper.selectChicken", param);
		}
	}
	
	public List<Chicken> selectChickenFromRowNum(int rowNum){
		try(SqlSession sqlSession = sqlSessionFactoryBean.newSqlSession()){
			Map<String, Object> param = new HashMap<>();
			param.put("rowNum", rowNum);
			return sqlSession.selectList("team.gtfm.chicken.chickenMapper.selectChickenFromRowNum", param);
		}
	}
	
	public List<SummaryChicken> selectSummaryChicken(int start, int limit){
		try(SqlSession sqlSession = sqlSessionFactoryBean.newSqlSession()){
			Map<String, Object> param = new HashMap<>();
			param.put("start", start);
			param.put("limit", limit);
			return sqlSession.selectList("team.gtfm.chicken.chickenMapper.selectSummaryChicken", param);
		}
	}
	
	public boolean insertChickenCoord(List<Coord> coordList){
		try(SqlSession sqlSession = sqlSessionFactoryBean.newSqlSession()){
			int count = 0;
			for(Coord coord : coordList){
				count += sqlSession.insert("team.gtfm.chicken.chickenMapper.insertChickenCoord", coord);
			}
			sqlSession.commit();
			System.out.println("insert: " + count + "/List Size: " + coordList.size());
			
			return (count > 0)? true : false;
		}
	}
}
