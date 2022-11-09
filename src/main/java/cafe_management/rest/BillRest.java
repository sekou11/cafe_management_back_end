package cafe_management.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path="/bill")
public interface BillRest {
	
	@PostMapping(path="/generateReport")
	public ResponseEntity<String>generateReport(@RequestBody Map<String, Object>requestMap);

}
