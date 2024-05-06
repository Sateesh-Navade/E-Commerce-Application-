package org.jsp.e_commerce.dao;

import java.util.Optional;

import org.jsp.e_commerce.model.User;
import org.jsp.e_commerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	private UserRepository userRepo;

	public User saveUser(User u) {
		return userRepo.save(u);
	}

	public User updateUser(User u) {
		return userRepo.save(u);
	}

	public Optional<User> findById(int id) {
		return userRepo.findById(id);
	}

	public Optional<User> VarifyByPhone(long phone, String password) {
		return userRepo.varifyByPhone(phone, password);
	}

	public Optional<User> VarifyByemail(String email, String password) {
		return userRepo.varify(email, password);
	}

}
