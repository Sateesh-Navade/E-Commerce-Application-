package org.jsp.e_commerce.service;

import java.util.Optional;

import org.jsp.e_commerce.dao.MerchantDao;
import org.jsp.e_commerce.dto.ResponseStructure;
import org.jsp.e_commerce.exception.IdNotFoundException;
import org.jsp.e_commerce.exception.InvalidCredentialsException;
import org.jsp.e_commerce.model.Merchant;
import org.jsp.e_commerce.util.Account_Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;

@Service
public class MerchantService {

	@Autowired
	private MerchantDao mdao;

	@Autowired
	private E_CommerceAppEmailService appEmailService;

	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant m, HttpServletRequest req) {
		ResponseStructure<Merchant> responseStructure = new ResponseStructure<>();

		m.setStatus(Account_Status.IN_ACTIVE.toString());
		m.setToken(RandomString.make(45));
		String message = appEmailService.sendWellcomeMail(m, req);

		responseStructure.setData(mdao.saveMerchant(m));
		responseStructure.setMessage("Merchant Created ," + message);
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant m) {
		ResponseStructure<Merchant> responseStructure = new ResponseStructure<>();
		responseStructure.setData(mdao.updateMerchant(m));
		responseStructure.setMessage("Merchant Updated");
		responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(responseStructure, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<Merchant>> findById(int id) {
		Optional<Merchant> recMerchant = mdao.findById(id);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setData(recMerchant.get());
			structure.setMessage("Merchant Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException("Inavlid ID, 🙏Plese Enter Valid Merchant ID");
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(long phone, String password) {
		Optional<Merchant> recMerchant = mdao.VarifyByPhone(phone, password);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setMessage("Verification Succesfull");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid Phone Number or Password");
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(String email, String password) {
		Optional<Merchant> recMerchant = mdao.VarifyByemail(email, password);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			Merchant m = recMerchant.get();
			if(m.getStatus().equals(Account_Status.IN_ACTIVE.toString())) {
				throw new IllegalArgumentException("Account is not activated ");
			}
			structure.setMessage("Verification Succesfull");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid Email or Password");
	}

	public ResponseEntity<ResponseStructure<String>> activate(String token) {
		Optional<Merchant> recMerchant = mdao.findByToken(token);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			Merchant merchant = recMerchant.get();
			merchant.setStatus(Account_Status.ACTIVE.toString());
			merchant.setToken(null);

			mdao.saveMerchant(merchant);

			structure.setMessage("Account Varified And Activted");
			structure.setData("Merchant Found");

			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("Invalid Email or Password");
	}
}
