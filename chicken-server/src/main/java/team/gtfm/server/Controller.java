package team.gtfm.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team.gtfm.server.bean.Chicken;
import team.gtfm.server.bean.LocalChicken;
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
			
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Allow-Origin", "*");
			return new ResponseEntity<>(chickenList, map, HttpStatus.OK);
		}catch(Exception e){
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value="/data/chicken/local/{city}/{gu}/{dong}", produces=CONTENT_TYPE)
	public ResponseEntity<?> getSelectChickenLocalList(@PathVariable String city,
													   @PathVariable String gu,
													   @PathVariable String dong){
		try{
			StringBuilder simpleAddrBuilder = new StringBuilder();
			simpleAddrBuilder.append(city).append(" ")
							.append(gu).append(" ")
							.append(dong);
			String simpleAddr = simpleAddrBuilder.toString();
			LocalChicken chickenList = chickenDao.selectLocalChickens(simpleAddr);
			
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Allow-Origin", "*");
			return new ResponseEntity<>(chickenList, map, HttpStatus.OK);
			
		}catch(Exception e){
			
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Allow-Origin", "*");
			e.printStackTrace(System.out);
			return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/data/address/{firstAddr}", produces=CONTENT_TYPE)
	public ResponseEntity<?> getAddressInfoWithFirst(@PathVariable String firstAddr){
		try{
			List<String> addrList = chickenDao.selectAddress(firstAddr);
			
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Allow-Origin", "*");
			return new ResponseEntity<>(addrList, map, HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace(System.out);
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value="/data/address/{firstAddr}/{secondAddr}", produces=CONTENT_TYPE)
	public ResponseEntity<?> getAddressInfoWithSecond(@PathVariable String firstAddr, @PathVariable String secondAddr){
		try{
			List<String> addrList = chickenDao.selectAddress(firstAddr, secondAddr);
			
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("Access-Control-Allow-Origin", "*");
			return new ResponseEntity<>(addrList, map, HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace(System.out);
			return ResponseEntity.badRequest().build();
		}
	}
}
