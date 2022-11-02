package cafe_management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import cafe_management.Utils.CafeUtils;
import cafe_management.Utils.EmailUtils;
import cafe_management.constant.CafeConstant;
import cafe_management.dao.UserDao;
import cafe_management.jwt.CustomerUserDetailsService;
import cafe_management.jwt.JwtFilter;
import cafe_management.jwt.JwtUtil;
import cafe_management.pojo.User;
import cafe_management.service.CafeService;
import cafe_management.wrapper.UserWrapper;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CafeServiceImpl implements CafeService {
	     @Autowired
        private UserDao userDao;
        @Autowired
	    AuthenticationManager authenticationManager;
         @Autowired
        private CustomerUserDetailsService customerUserDetailsService;
         @Autowired
         JwtFilter jwtFilter;
         @Autowired
         JwtUtil JwtUtil;
         @Autowired
         EmailUtils emailUtils;
	 
	@Override
	public ResponseEntity<String> signup(Map<String, String> requestMap) {
		  log.info(" Insigne singup {}",requestMap);
		 try {
			if (valideSignupRequest(requestMap)) {
				 User user = userDao.findByEmailId(requestMap.get("email"));
				   if (Objects.isNull(user)) {
					 userDao.save(getUserFromMap(requestMap));
					 return CafeUtils.getResponse("User Saved Successfuly!!!", HttpStatus.OK);
				} else {
					 return CafeUtils.getResponse("Email Already Exists", HttpStatus.BAD_REQUEST);
				}
			}else {
				return CafeUtils.getResponse(CafeConstant.INVALID_DATA, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		 return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private User getUserFromMap(Map<String, String> requestMap) {
		 User user = new User();
		 user.setName(requestMap.get("name"));
		 user.setEmail(requestMap.get("email"));
		 user.setPassword(requestMap.get("password"));
		 user.setContactNumber(requestMap.get("contactNumber"));
		 user.setStatus("False");
		 user.setRole("User");
		return user;
	}

	private boolean valideSignupRequest(Map<String, String> requestMap) {
		  if (
				  requestMap.containsKey("name")&&
				   requestMap.containsKey("email")&&
				   requestMap.containsKey("password")&&
				   requestMap.containsKey("contactNumber")
				  ) {
			return true;
		}
		return false;
	}

	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		 log.info("Inside Login");
		   try {
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(requestMap.get("email"),requestMap.get("password")));
			  if (auth.isAuthenticated()) {
				  if (customerUserDetailsService.getUserDetails().getStatus().equalsIgnoreCase("true")) {
					return new ResponseEntity<String>("{\"token\":\""+JwtUtil.generateToken(customerUserDetailsService.getUserDetails().getEmail(), customerUserDetailsService.getUserDetails().getRole())+"\"}",
							HttpStatus.OK);
				}else {
					return new ResponseEntity<String>("{\"message\":\""+"wait for admin approval."+"\"}",
							HttpStatus.BAD_REQUEST);
				}
			}
		} catch (Exception ex) {
			log.error("{}",ex);
		}
		   return new ResponseEntity<String>("{\"message\":\""+"Bad Credentials."+"\"}",
					HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<List<UserWrapper>> getAllUsers() {
		try {
			if (jwtFilter.isAdmin()) {
				return new ResponseEntity<>(new ArrayList<>(userDao.getAllUser()),HttpStatus.OK);
			}else {
				return new ResponseEntity<>(new ArrayList<>(),HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		try {
			  if (jwtFilter.isAdmin()) {
				 Optional<User> optional = userDao.findById(Integer.parseInt(requestMap.get("id")));
				    if (!optional.isEmpty()) {
						userDao.updateStatus(requestMap.get("status"),Integer.parseInt(requestMap.get("id")));
						sendMailToAllAdmin(requestMap.get("status"),optional.get().getEmail(),userDao.getAllAdmin());
						return new ResponseEntity<String>("User Status Updated successfuly",HttpStatus.OK);
					} else {
                      return new ResponseEntity<String>("User id does not exist",HttpStatus.NOT_FOUND);
					}
			} else {
				 return CafeUtils.getResponse(CafeConstant.UNAUTHORIZED_ACCES, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		 return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
		
		allAdmin.remove(jwtFilter.getCurrentUser());
		if (status!=null && status.equalsIgnoreCase("true")) {
			emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Approved",
					"User:-" +user+ "\n is approved by \n Admin:-"+jwtFilter.getCurrentUser(),  allAdmin);
		} else {
			emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Disabled",
					"User:-" +user+ "\n is approved by \n Admin:-"+jwtFilter.getCurrentUser(),  allAdmin);
		}
	}

	@Override
	public ResponseEntity<String> checkToken() {
		
		 return CafeUtils.getResponse("true",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
		  try {
			   User userObj = userDao.findByEmail(jwtFilter.getCurrentUser());
			     if (!userObj.equals(null)) {
					 if (userObj.getPassword().equals(requestMap.get("oldPassword"))) {
						userObj.setPassword(requestMap.get("newPassword"));
						userDao.save(userObj);
						return  CafeUtils.getResponse("Password updated sucessfuly", HttpStatus.OK);
					}
					 return CafeUtils.getResponse("Incorrect Old Password", HttpStatus.BAD_REQUEST);
				} else {
					return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			  
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return CafeUtils.getResponse(CafeConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	

}
