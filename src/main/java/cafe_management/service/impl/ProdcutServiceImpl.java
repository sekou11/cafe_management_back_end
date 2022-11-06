package cafe_management.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cafe_management.Utils.CafeUtils;
import cafe_management.constant.CafeConstant;
import cafe_management.dao.ProductDao;
import cafe_management.jwt.JwtFilter;
import cafe_management.pojo.Category;
import cafe_management.pojo.Product;
import cafe_management.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProdcutServiceImpl implements ProductService {
	 @Autowired
   ProductDao productDao;
	  @Autowired
	  private JwtFilter jwtFilter;
	@Override
	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
	    try {
	    	 if (jwtFilter.isAdmin()) {
	    		  if (validateProductMap(requestMap ,false)) {
					productDao.save(getProductFromMap(requestMap,false));
					 return CafeUtils.getResponse("Product Added Successfuly", HttpStatus.OK);
				} else {
                   return CafeUtils.getResponse(CafeConstant.INVALID_DATA, HttpStatus.BAD_REQUEST);
				}
				
			} else {
              return CafeUtils.getResponse(CafeConstant.UNAUTHORIZED_ACCES, HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	private Product getProductFromMap(Map<String, String> requestMap, boolean issAdd) {
		Category category =new Category();
		category.setId(Integer.parseInt( requestMap.get("categoryId") ));
		
		Product product = new Product();
		   if (issAdd) {
			product.setId(Integer.parseInt(requestMap.get("id")));
		}else {
			product.setStatus("true");
		}
		   product.setCategory(category);
		   product.setName(requestMap.get("name"));
		   product.setPrice(Integer.parseInt( requestMap.get("price")));
		   product.setDescription(requestMap.get("description"));
		   
		   
		return product;
	}
	private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
		 if (requestMap.containsKey("name")) {
			 if (requestMap.containsKey("id") && validateId) {
				return true;
			} else if(!validateId){
				return true;
			}
		}
		return false;
	}

}
