package cafe_management.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path="/product")
public interface ProductRest {
   
	@PostMapping(path="/add")
	public ResponseEntity<String>addNewProduct(@RequestBody Map<String, String>requestMap);
}
