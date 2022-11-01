package cafe_management.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import cafe_management.pojo.User;
import cafe_management.wrapper.UserWrapper;

public interface UserDao extends JpaRepository<User, Integer> {

	public User findByEmailId(@Param("email") String email);
	
	public List<UserWrapper>getAllUser();
    @Transactional
    @Modifying
	public Integer updateStatus(@Param ("status") String status,@Param ("id")Integer id);

	public List<String> getAllAdmin();

}
