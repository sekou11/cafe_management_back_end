package cafe_management.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cafe_management.wrapper.UserWrapper;

@RequestMapping("user")
public interface CafeRest {
	
	@PostMapping("/signup")
  public ResponseEntity<String>signup(@RequestBody(required = true)Map<String, String>requestMap);
	
	@PostMapping("/login")
	  public ResponseEntity<String>login(@RequestBody(required = true)Map<String, String>requestMap);
	
	 @GetMapping(path = "/get")
	public ResponseEntity<List<UserWrapper>> getAllUsers();
	 
	 @PostMapping(path = "/update")
	 public ResponseEntity<String>update(@RequestBody(required = true)Map<String, String>requestMap);
	 
	 @GetMapping(path = "/checkToken")
	 public ResponseEntity<String>checkToken();
	 
	 @PostMapping(path = "/changePassword")
	 public ResponseEntity<String>changePassword(@RequestBody(required = true)Map<String, String>requestMap);
}
