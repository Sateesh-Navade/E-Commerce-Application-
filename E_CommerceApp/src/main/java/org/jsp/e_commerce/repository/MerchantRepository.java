package org.jsp.e_commerce.repository;

import java.util.Optional;

import org.jsp.e_commerce.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {

	@Query("select m from Merchant m where m.phone=?1 and m.password=?2")
	public Optional<Merchant> varifyByPhone(long phone, String password);
	
	@Query("select m from Merchant m where m.email=?1 and m.password=?2")
	public Optional<Merchant> varifyByEmail(String email, String password);
	//or
//	public Optional<Merchant> findByEmailAndPassword(String email, String password);
	
	public Optional<Merchant> findByToken(String token);
}
 