package cafe_management.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import cafe_management.wrapper.ProductWrapper;

public interface ProductService {

	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap);

	public ResponseEntity<List<ProductWrapper>> getAllProduct();

	public ResponseEntity<String> update(Map<String, String> requestMap);

	public ResponseEntity<String> deleteProduct(Integer id);

	public ResponseEntity<String> updateStatus(Map<String, String> requestMap);

	public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id);

}
