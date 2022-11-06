package cafe_management.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import cafe_management.pojo.Category;

public interface CategoryService {

	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap);

	public ResponseEntity<List<Category>> getallCategory(String filterValue);

}
