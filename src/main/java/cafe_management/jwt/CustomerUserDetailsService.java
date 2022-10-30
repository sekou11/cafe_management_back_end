package cafe_management.jwt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import cafe_management.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CustomerUserDetailsService implements UserDetailsService {
	@Autowired
  private UserDao userDao;
	private  cafe_management.pojo.User userDetails;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 log.info("Insigne loadUserByUsername {} ",username);
		userDetails = userDao.findByEmailId(username);
		 if (!Objects.isNull(userDetails)) {
			return new User(userDetails.getEmail(), userDetails.getPassword(), new ArrayList<>());
		}else {
			 throw new UsernameNotFoundException(" user not found");
		}
	 
	}
	public cafe_management.pojo.User getUserDetails(){
		 return userDetails;
	 }
	

}
