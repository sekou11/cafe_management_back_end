package cafe_management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cafe_management.pojo.Product;
import cafe_management.wrapper.ProductWrapper;

public interface ProductDao extends JpaRepository<Product, Integer> {

	public List<ProductWrapper> getAllProduct();



}
