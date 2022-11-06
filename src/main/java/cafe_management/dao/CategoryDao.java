package cafe_management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cafe_management.pojo.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {
	
	public List<Category>getAllCategory();

}
