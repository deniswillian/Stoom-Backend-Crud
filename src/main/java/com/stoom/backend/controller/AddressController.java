package com.stoom.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stoom.backend.model.Address;
import com.stoom.backend.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
    AddressService addressService;
	
	//Index
	@GetMapping
	public List<Address> getAllAddress() {
	    return addressService.findAllAddress();
	}
	
	//POST
	@PostMapping
	public Address createAddress(@Valid @RequestBody Address address) {
	    return addressService.saveAddress(address);
	}
	
	//GET
	@GetMapping("/{id}")
	public Address getAddressById(@PathVariable Long id) {
	    return addressService.findByIdAddress(id);
	}
	
	// Update a Note
	@PutMapping("/{id}")
	public Address updateAddress(@PathVariable Long id, @Valid @RequestBody Address address) {
	    return addressService.updateAddress(id, address);
	}
	
	//DEL
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAddressById(@PathVariable Long id) {
	    return addressService.deleteByIdAddress(id);
	}
	
	
	

}
