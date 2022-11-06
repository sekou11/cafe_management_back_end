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
import cafe_management.pojo.Category;
import cafe_management.rest.CategoryRest;
import cafe_management.service.CategoryService;

@RestController
public class CategoryRestimpl implements CategoryRest {
	@Autowired
	private CategoryService categoryService;

	@Override
	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
		try {
			return categoryService.addNewCategory(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Category>> getallCategory(String filterValue) {
		 try {
			return categoryService.getallCategory(filterValue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity< >(new ArrayList<>() ,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	

	

}
