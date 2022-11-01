package cafe_management.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import cafe_management.wrapper.UserWrapper;

public interface CafeService {

	ResponseEntity<String> signup(Map<String, String> requestMap);

	ResponseEntity<String> login(Map<String, String> requestMap);

	ResponseEntity<List<UserWrapper>> getAllUsers();

	ResponseEntity<String> update(Map<String, String> requestMap);

}
