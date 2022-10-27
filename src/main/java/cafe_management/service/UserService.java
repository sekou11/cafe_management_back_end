package cafe_management.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface UserService {

	public ResponseEntity<String> signup(Map<String, String> requestMap);

	public ResponseEntity<String> login(Map<String, String> requestMap);

}
