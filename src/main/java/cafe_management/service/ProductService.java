package cafe_management.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface ProductService {

	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap);

}
