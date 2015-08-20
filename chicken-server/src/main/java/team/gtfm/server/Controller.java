package team.gtfm.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team.gtfm.server.bean.Chicken;
import team.gtfm.server.db.ChickenDao;

@RestController
public class Controller {
	private static final String CONTENT_TYPE = "application/json; charset=utf8";
	
	@Autowired
	private ChickenDao chickenDao;
	
	@RequestMapping(value="/data/chicken/{limit}", produces=CONTENT_TYPE)
	public ResponseEntity<?> getSelectChickenList(@PathVariable int limit){
		try{
			List<Chicken> chickenList = chickenDao.selectChicken(limit);
			return ResponseEntity.ok().body(chickenList);
		}catch(Exception e){
			return ResponseEntity.badRequest().build();
		}
	}
}
