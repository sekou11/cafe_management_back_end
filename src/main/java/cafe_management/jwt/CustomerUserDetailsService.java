package cafe_management.jwt;

import java.util.ArrayList;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cafe_management.dao.UserDao;
import cafe_management.pojo.User;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class CustomerUserDetailsService implements UserDetailsService {
	
	 @Autowired
     UserDao userDao;
	
    
	private User userDetail;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Inside loadUserByUsername{}",username);
		userDetail  = userDao.findByEmailId(username);
		 if (!Objects.isNull(userDetail)) {
			return new org.springframework.security.core.userdetails.User(
					userDetail.getEmail(), userDetail.getPassword()	, new ArrayList<>());
		}
		 else {
			throw new UsernameNotFoundException("User Not Found....");
		}
		
	}
	public User getUserDetails() {
		return userDetail;
	}

}
