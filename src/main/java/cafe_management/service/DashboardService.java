package cafe_management.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface DashboardService {

	ResponseEntity<Map<String, Object>> getAllDashboard();

}
