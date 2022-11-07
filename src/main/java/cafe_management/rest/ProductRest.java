package cafe_management.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cafe_management.wrapper.ProductWrapper;

@RequestMapping(path="/product")
public interface ProductRest {
   
	@PostMapping(path="/add")
	public ResponseEntity<String>addNewProduct(@RequestBody Map<String, String>requestMap);
	
	@GetMapping(path="/get")
	public ResponseEntity<List<ProductWrapper>>getAllProduct();
	
	@PostMapping(path="/update")
	public ResponseEntity<String>update(@RequestBody Map<String, String>requestMap);
	
	 @PostMapping(path="/delete/{id}")
	public ResponseEntity<String>deleteProduct(@PathVariable Integer id);
}