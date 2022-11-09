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

@NamedQuery(name = "Bill.getAllBills" ,query = "SELECT b FROM Bill b ORDER BY b.id DESC")

@NamedQuery(name = "Bill.billByUserName" ,query = "SELECT b FROM Bill b WHERE b.createdBy =:userName ORDER BY b.id DESC ")


@Entity
@DynamicUpdate
@DynamicInsert
@Data
@Table(name = "bill")
public class Bill implements Serializable {
  
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "contactnumber")
	private String contactNumber;
	
	@Column(name = "paymentMethod")
	private String paymentMethod;
	
	@Column(name = "total")
	private Integer total;
	
	@Column(name = "productDetails",columnDefinition = "json")
	private String productDetails;
	
	
	@Column(name = "createdBy")
	private String createdBy;
	
	
	

	

}
