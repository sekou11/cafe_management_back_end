package cafe_management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import cafe_management.pojo.User;
import cafe_management.wrapper.UserWrapper;

public interface UserDao extends JpaRepository<User, Integer> {

	public User findByEmailId(@Param("email") String email);
	
	public List<UserWrapper>getAllUser();

}
