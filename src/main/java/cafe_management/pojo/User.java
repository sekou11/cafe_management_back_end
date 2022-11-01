package cafe_management.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
@NamedQuery( name="User.findByEmailId",
             query="SELECT u FROM User u WHERE u.email =:email"
             )
//select all a user who role=user
@NamedQuery( name="User.getAllUser",
query="SELECT new cafe_management.wrapper.UserWrapper(u.id,u.name,u.email,u.contactNumber,u.status) FROM User u WHERE u.role ='user'"
)

@NamedQuery(name = "User.updateStatus" ,
           query ="UPDATE User u "
           		+ "SET u.status=:status"
           		+ " WHERE u.id=:id" )



@Entity
@DynamicUpdate
@DynamicInsert
@Data
@Table(name = "user")
public class User implements Serializable {
  
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "contactNumber")
	private String contactNumber;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "role")
	private String role;
	

}
