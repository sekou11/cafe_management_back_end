package cafe_management.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import cafe_management.pojo.Product;
import cafe_management.wrapper.ProductWrapper;

public interface ProductDao extends JpaRepository<Product, Integer> {

	public List<ProductWrapper> getAllProduct();
   
	@Modifying
	@Transactional
	public Integer updateStatus(@Param(value = "id") Integer id, @Param(value = "status") String status);

	public List<ProductWrapper> getProductByCategory(@Param("id") Integer id);



}
