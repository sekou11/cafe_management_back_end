package cafe_management.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("user")
public interface CafeRest {
	
	@PostMapping("/signup")
  public ResponseEntity<String>signup(@RequestBody(required = true)Map<String, String>requestMap);
	
	@PostMapping("/login")
	  public ResponseEntity<String>login(@RequestBody(required = true)Map<String, String>requestMap);
}
