package org.jsp.e_commerce.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
//@Getter
//@Setter
@ToString(exclude = "password")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Merchant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false , unique = true)
	private long phone;
	
	@Column(nullable = false , unique = true)
	private String gst_number;
	
	@Column(nullable = false , unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(nullable = false)
	private int age;

	private String status;
	
	private String token;
	
	@OneToMany(mappedBy = "merchant")
	private List<Product> products;
}