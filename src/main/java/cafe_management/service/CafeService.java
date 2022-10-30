package cafe_management.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface CafeService {

	ResponseEntity<String> signup(Map<String, String> requestMap);

	ResponseEntity<String> login(Map<String, String> requestMap);

}
