package org.jsp.e_commerce.dao;

import java.util.Optional;

import org.jsp.e_commerce.model.Merchant;
import org.jsp.e_commerce.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {

	@Autowired
	private MerchantRepository mRepo;
	
	public Merchant saveMerchant(Merchant merchant) {
		return mRepo.save(merchant);
	}
	
	public Merchant updateMerchant(Merchant m) {
		return mRepo.save(m);
	}

	public Optional<Merchant> findById(int id) {
		return mRepo.findById(id);
	}
	
	public Optional<Merchant> VarifyByPhone(long phone ,String password){
		return mRepo.varifyByPhone(phone, password);
	}
	public Optional<Merchant> VarifyByemail(String email ,String password){
		return mRepo.varifyByEmail(email, password);
	}
	public Optional<Merchant> findByToken(String token){
		return mRepo.findByToken(token);
	}

}
