package cafe_management.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cafe_management.dao.BillDao;
import cafe_management.dao.CategoryDao;
import cafe_management.dao.ProductDao;
import cafe_management.service.DashboardService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {
	private CategoryDao categoryDao;
	private ProductDao productDao;
	private BillDao billDao;

	@Override
	public ResponseEntity<Map<String, Object>> getAllDashboard() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category contains ", categoryDao.count() );
		map.put("product contains ", productDao.count());
		map.put("bill contains ", billDao.count());
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
