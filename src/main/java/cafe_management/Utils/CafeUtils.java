package cafe_management.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

public class CafeUtils {
	private CafeUtils() {
		
	}

	public static ResponseEntity<String> getResponse(String responseMessage, HttpStatus httpStatus) {
		
		return new ResponseEntity<String>("{ \"message\" : \"" + responseMessage+ "\"}" ,httpStatus);
	}
	
	public static String getUUID() {
        Date date = new Date();
		Long time = date.getTime();
		return "Bill - " + time;
	}
	
	public static JSONArray getJsonArrayFromString(String data) throws JSONException{
		JSONArray jsonArray = new JSONArray();
		return jsonArray;
	}
	
	public static Map<String, Object>getMapFromJson(String data){
		if (!Strings.isNullOrEmpty(data)) 
			return new Gson().fromJson(data,new TypeToken<Map<String, Object>>(){
				}.getType());
			
	return new HashMap<>();
	}

	

}
