package cafe_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import cafe_management.pojo.User;

public interface UserDao extends JpaRepository<User, Integer> {

	public User findByEmailId(@Param("email") String email);

}
