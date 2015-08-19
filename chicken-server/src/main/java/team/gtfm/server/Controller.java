package team.gtfm.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import team.gtfm.server.bean.Chicken;
import team.gtfm.server.bean.SummaryChicken;
import team.gtfm.server.db.ChickenDao;

@RestController
public class Controller {
	private static final String CONTENT_TYPE = "application/json; charset=utf8";
	
	@Autowired
	private ChickenDao chickenDao;
	
//	@RequestMapping(value="data/chicken", method=RequestMethod.GET, produces=CONTENT_TYPE)
//	public List<Chicken> getChickens(@RequestParam(value="limit", defaultValue="10") int limit){
//		
//		return chickenDao.selectChicken(limit);
//	}
//
//	@RequestMapping(value="data/chicken/summary-chicken", method=RequestMethod.GET, produces=CONTENT_TYPE)
//	public List<SummaryChicken> getSummaryChickens(@RequestParam(value="start", defaultValue="0") int start,
//												@RequestParam(value="limit", defaultValue="10") int limit){
//		return chickenDao.selectSummaryChicken(start, limit);
//	}
	
//	@RequestMapping(value="data/chicken/coord", method=RequestMethod.POST, consumes=CONTENT_TYPE, produces=CONTENT_TYPE)
//	public ResponseEntity<Result> postChickenCoord(@RequestBody List<Coord> coordList){
//		
//		Result result = new Result();
//		result.setResult(chickenDao.insertChickenCoord(coordList));
//		return ResponseEntity.ok(result);
//	}
}
