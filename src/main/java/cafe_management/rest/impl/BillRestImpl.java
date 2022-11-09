package cafe_management.rest.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cafe_management.Utils.CafeUtils;
import cafe_management.constant.CafeConstant;
import cafe_management.pojo.Bill;
import cafe_management.rest.BillRest;
import cafe_management.service.BillService;

@RestController
public class BillRestImpl implements BillRest {
	  @Autowired
	private BillService billService;

	@Override
	public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
		   try {
			return billService.generateReport(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG	, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Bill>> getBill() {
		  try {
			return billService.getBills();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		  return null;
	}
	
	

}
