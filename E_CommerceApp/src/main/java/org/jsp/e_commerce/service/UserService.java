package org.jsp.e_commerce.service;

import java.util.Optional;

import org.jsp.e_commerce.dao.UserDao;
import org.jsp.e_commerce.dto.ResponseStructure;
import org.jsp.e_commerce.exception.IdNotFoundException;
import org.jsp.e_commerce.exception.InvalidCredentialsException;
import org.jsp.e_commerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User u) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
//		u=userDao.saveUser(u);
		responseStructure.setData(userDao.saveUser(u));
		responseStructure.setMessage("User Saved Successfully");
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		return new  ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.CREATED);
		
	}
	public ResponseEntity<ResponseStructure<User>> updateUser(User u) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
//		u=userDao.saveUser(u);
		responseStructure.setData(userDao.updateUser(u));
		responseStructure.setMessage("User Updated Successfully");
		responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new  ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.ACCEPTED);
		
	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {
		Optional<User> recUser = userDao.findById(id);
		ResponseStructure<User> stru = new ResponseStructure<>();
		if (recUser.isPresent()) {
			stru.setData(recUser.get());
			stru.setMessage("User Found");
			stru.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(stru, HttpStatus.OK);
		}
		throw new IdNotFoundException("Inavlid ID, 🙏Plese Enter Valid User ID");
	}

	public ResponseEntity<ResponseStructure<User>> verifyUser(String email, String password) {
		Optional<User> recUser = userDao.VarifyByemail(email, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recUser.isPresent()) {
			structure.setMessage("Verification Succesfull");
			structure.setData(recUser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid Email or Password");
	}

	public ResponseEntity<ResponseStructure<User>> verifyUserByPhone(long phone, String password) {
		Optional<User> recMerchant = userDao.VarifyByPhone(phone, password);
		ResponseStructure<User> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setMessage("Verification Succesfull");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid Phone Number or Password🤔");
	}

}
