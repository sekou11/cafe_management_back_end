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
import cafe_management.rest.ProductRest;
import cafe_management.service.ProductService;
import cafe_management.wrapper.ProductWrapper;

@RestController
public class ProductRestImpl implements ProductRest {
	@Autowired
    ProductService productService;
    
	@Override
	public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
		 try {
			return productService.addNewProduct(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<ProductWrapper>> getAllProduct() {
		try {
			return productService.getAllProduct();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<ProductWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		 try {
			return productService.update(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		 return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteProduct(Integer id) {
		try {
			return productService.deleteProduct(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
		try {
			return productService.updateStatus(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id) {
		   try {
			return productService.getByCategory(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<ProductWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<ProductWrapper> getProductById(Integer id) {
	    try {
			return productService.getProductById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<ProductWrapper>(new ProductWrapper(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
}
