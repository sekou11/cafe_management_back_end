package cafe_management.pojo;

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

@NamedQuery(name = "User.findByEmailId" ,query = "SELECT u FROM User u WHERE u.email=:email")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "user")
public class User {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id")
	private Integer id;
     
     @Column(name = "name")
     private String name;
     
     @Column(name = "contactNumber")
     private String contactNumber;
     
     @Column(name = "email")
     private String email;
     
     @Column(name = "password")
     private String password;
     
     @Column(name = "status")
     private String status;
     
     @Column(name = "role")
     private String role;
	
}
