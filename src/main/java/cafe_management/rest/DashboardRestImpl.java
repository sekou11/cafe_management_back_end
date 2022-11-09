package cafe_management.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cafe_management.service.DashboardService;

@RestController
public class DashboardRestImpl implements DashboardRest {
	  @Autowired
     DashboardService dashboardService;
	@Override
	public ResponseEntity<Map<String, Object>> getAllDashboard() {
		
		  
			return dashboardService.getAllDashboard();
		
	}

}
