package org.jsp.e_commerce.service;


import java.util.Optional;

import org.jsp.e_commerce.dao.AddressDao;
import org.jsp.e_commerce.dto.ResponseStructure;
import org.jsp.e_commerce.exception.AddressNotFoundException;
import org.jsp.e_commerce.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	
	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address){
		ResponseStructure<Address> structure = new ResponseStructure<>();
		addressDao.saveAddress(address);
		structure.setData(addressDao.saveAddress(address));
		structure.setMessage("address has been saved");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ResponseStructure<Address>> updateAddress(Address address){
		ResponseStructure<Address> structure = new ResponseStructure<>();
		Optional<Address> recAddress = addressDao.findById(address.getId());
		if(recAddress.isPresent()) {
			Address dbaddress = recAddress.get();
			dbaddress.setAddresstype(address.getAddresstype());
			dbaddress.setBuildingname(address.getBuildingname());
			dbaddress.setCity(address.getCity());
			dbaddress.setCountry(address.getCountry());
			dbaddress.setHousenumber(address.getHousenumber());
			dbaddress.setLandmark(address.getLandmark());
			dbaddress.setPincode(address.getPincode());
			dbaddress.setState(address.getState());
			structure.setData(addressDao.saveAddress(dbaddress));
			structure.setMessage("address has been updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.ACCEPTED);
		}
		throw new AddressNotFoundException("Addres not found");
	}
}
