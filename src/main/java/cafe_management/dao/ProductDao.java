package cafe_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cafe_management.pojo.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {

}
