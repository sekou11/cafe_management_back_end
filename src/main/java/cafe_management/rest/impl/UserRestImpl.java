package cafe_management.rest.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cafe_management.Utils.CafeUtils;
import cafe_management.constants.CafeConstants;
import cafe_management.rest.UserRest;
import cafe_management.service.UserService;

@RestController
public class UserRestImpl implements UserRest {
	@Autowired
  private UserService userService;
	@Override
	public ResponseEntity<String> signup(Map<String, String> requestMap) {
		try {
			return userService.signup(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
