package com.stoom.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.maps.model.GeocodingResult;
import com.stoom.backend.exception.ResourceNotFoundException;
import com.stoom.backend.model.Address;
import com.stoom.backend.repository.AddressRepository;
import com.stoom.backend.util.GoogleMapService;

@Service
public class AddressService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private GoogleMapService googleMapService;

	public List<Address> findAllAddress() {
		return addressRepository.findAll();
	}

	public Address saveAddress(Address address) {

		if ((address.getLatitude() == null) || (address.getLongitude() == null)) {

			address = this.getCoordinatesFromAddress(address);

		}

		return addressRepository.save(address);
	}

	public Address findByIdAddress(Long addressId) {
		return addressRepository.findById(addressId)
				.orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));
	}

	public Address updateAddress(Long addressId, Address address) {

		Address addressExisting = addressRepository.findById(addressId)
				.orElseThrow(() -> new ResourceNotFoundException("Address", "id", addressId));

		BeanUtils.copyProperties(address, addressExisting, "id");

		addressExisting = this.saveAddress(addressExisting);

		return addressExisting;

	}

	public ResponseEntity<?> deleteByIdAddress(Long addressId) {

		Address address = this.findByIdAddress(addressId);

		addressRepository.delete(address);

		String logMessage = String.format("Deleted address using id = %s", addressId);

		logger.info(logMessage);

		return ResponseEntity.ok().build();
	}

	public Address getCoordinatesFromAddress(Address address) {

		List<String> fields = new ArrayList<String>();

		fields.add(address.getStreetName());

		fields.add(address.getNumber());

		fields.add(address.getCity());

		fields.add(address.getZipcode());

		String place = String.join(",", fields);

		GeocodingResult geocodingResult = googleMapService.getCoordinatesFromAddress(place);

		address.setLatitude(geocodingResult.geometry.location.lat);

		address.setLongitude(geocodingResult.geometry.location.lng);

		return address;
	}

}
