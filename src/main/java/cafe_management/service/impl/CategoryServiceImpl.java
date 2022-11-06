package cafe_management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import cafe_management.Utils.CafeUtils;
import cafe_management.constant.CafeConstant;
import cafe_management.dao.CategoryDao;
import cafe_management.jwt.JwtFilter;
import cafe_management.pojo.Category;
import cafe_management.service.CategoryService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
	 @Autowired
      CategoryDao categoryDao;
	  @Autowired
	  JwtFilter jwtFilter;
	  
	@Override
	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
		try {
			  if (jwtFilter.isAdmin()) {
				  if (validateCategoryMap(requestMap, false)) {
					categoryDao.save(getCategoryFromMap(requestMap, false));
					return CafeUtils.getResponse("Category Added Successfuly", HttpStatus.OK);
				}
			} else {
              return CafeUtils.getResponse(CafeConstant.UNAUTHORIZED_ACCES, HttpStatus.UNAUTHORIZED);
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {
		  if (requestMap.containsKey("name")) {
			 if (requestMap.containsKey("id") && validateId) {
				 return true;
			} else if(!validateId) {
              return true;
			}
		}
		return false;
	}
	
	private Category getCategoryFromMap(Map<String, String>requestMap,Boolean isAdd) {
		Category category = new Category();
		  if (isAdd) {
			category.setId(Integer.parseInt(requestMap.get("id")));
		}
		   category.setName(requestMap.get("name"));
		  return category;
	}

	@Override
	public ResponseEntity<List<Category>> getallCategory(String filterValue) {
		try {
			 if (!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
				   log.info("Inside If...");
				return new ResponseEntity<List<Category>>(categoryDao.getAllCategory() ,HttpStatus.OK);
			}
			 return new ResponseEntity<List<Category>>(categoryDao.findAll() ,HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Category> >(new ArrayList<>() ,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		try {
			 if (jwtFilter.isAdmin())
			 {
				 if (validateCategoryMap(requestMap, true)) {
					    Optional<Category> optional = categoryDao.findById(Integer.parseInt( requestMap.get("id")));
					      if (!optional.isEmpty()) {
							  categoryDao.save(getCategoryFromMap(requestMap, true));
							  return CafeUtils.getResponse("Category Updated Successfuuly", HttpStatus.OK);
						} else {
                          return CafeUtils.getResponse("Category Id does not Found", HttpStatus.NOT_FOUND);
						}
				} else {
                 return CafeUtils.getResponse(CafeConstant.INVALID_DATA, HttpStatus.BAD_REQUEST);
				}
				
			}
			 else {
				 return CafeUtils.getResponse(CafeConstant.UNAUTHORIZED_ACCES, HttpStatus.UNAUTHORIZED);
			 }
			 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}