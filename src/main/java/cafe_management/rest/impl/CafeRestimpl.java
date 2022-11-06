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
import cafe_management.rest.CafeRest;
import cafe_management.service.CafeService;
import cafe_management.wrapper.UserWrapper;
@RestController
public class CafeRestimpl implements CafeRest {
	 @Autowired
   private CafeService cafeService;
	@Override
	public ResponseEntity<String> signup(Map<String, String> requestMap) {
		try {
			return cafeService.signup(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		
		try {
			return cafeService.login(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<List<UserWrapper>> getAllUsers() {
		
		try {
			return cafeService.getAllUsers();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);

}
	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
	try {
		return cafeService.update(requestMap);
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<String> checkToken() {
		try {
			return cafeService.checkToken();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
		try {
			return cafeService.changePassword(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
		try {
			return cafeService.forgotPassword(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
