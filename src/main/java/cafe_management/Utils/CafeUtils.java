package cafe_management.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cafe_management.constants.CafeConstants;

public class CafeUtils {
	private CafeUtils() {
		
	}

	public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
	
		return new ResponseEntity<String>("{ \"message\" : \"" + responseMessage+ "\"}" ,httpStatus);
	}
	
	

}
