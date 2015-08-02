package team.gtfm.server.db;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team.gtfm.server.bean.Chicken;

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
}
