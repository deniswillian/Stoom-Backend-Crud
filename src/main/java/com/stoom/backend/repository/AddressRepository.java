package com.stoom.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stoom.backend.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}