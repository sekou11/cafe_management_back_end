package cafe_management.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface BillService {

	ResponseEntity<String> generateReport(Map<String, Object> requestMap);

}
