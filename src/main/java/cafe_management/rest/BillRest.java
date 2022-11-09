package cafe_management.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cafe_management.pojo.Bill;

@RequestMapping(path="/bill")
public interface BillRest {
	
	@PostMapping(path="/generateReport")
	public ResponseEntity<String>generateReport(@RequestBody Map<String, Object>requestMap);
	
	@GetMapping(path="/get")
	public ResponseEntity<List<Bill>>getBill();

}
