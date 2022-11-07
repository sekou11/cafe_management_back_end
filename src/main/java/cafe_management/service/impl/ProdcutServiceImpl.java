package cafe_management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import cafe_management.wrapper.ProductWrapper;
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
	@Override
	public ResponseEntity<List<ProductWrapper>> getAllProduct() {
	   try {
		 return new ResponseEntity<> (productDao.getAllProduct() ,HttpStatus.OK);
	} catch (Exception ex) {
		ex.printStackTrace();
	}
		return new ResponseEntity<List<ProductWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		  try {
			  if (jwtFilter.isAdmin()) {
				   if (validateProductMap(requestMap, true)) {
					 Optional<Product> optional = productDao.findById(Integer.parseInt( requestMap.get("id")));
					    if (!optional.isEmpty()) {
							Product p = getProductFromMap(requestMap, true);
							p.setStatus(optional.get().getStatus());
							productDao.save(p);
							return CafeUtils.getResponse("Product Updated Successfully", HttpStatus.OK);
						} else {
							return CafeUtils.getResponse("Product Id Not Found...", HttpStatus.NOT_FOUND);
						}
				} else {
					return CafeUtils.getResponse(CafeConstant.INVALID_DATA, HttpStatus.BAD_REQUEST);
				}
				
			}else {
				return CafeUtils.getResponse(CafeConstant.UNAUTHORIZED_ACCES, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		  return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@Override
	public ResponseEntity<String> deleteProduct(Integer id) {
		  try {
			  if (jwtFilter.isAdmin()) {
				 Optional<Product> optional = productDao.findById(id);
				   if (!optional.isEmpty()) {
					productDao.deleteById(id);
					return CafeUtils.getResponse("Product deleted Successfuly", HttpStatus.OK);
				} else {
					return CafeUtils.getResponse("Product id Not Found", HttpStatus.NOT_FOUND);
				}
			}else {
				return CafeUtils.getResponse(CafeConstant.UNAUTHORIZED_ACCES, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
