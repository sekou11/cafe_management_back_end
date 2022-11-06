package cafe_management.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface CategoryService {

	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap);

}
