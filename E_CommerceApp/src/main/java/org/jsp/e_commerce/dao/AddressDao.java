package org.jsp.e_commerce.dao;


import java.util.Optional;

import org.jsp.e_commerce.model.Address;
import org.jsp.e_commerce.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AddressDao {
	@Autowired
	private AddressRepository addressRepository;
	
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}
	
	public Optional<Address> findById(int id){
		return addressRepository.findById(id);
	}
}
