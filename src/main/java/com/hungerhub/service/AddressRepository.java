package com.hungerhub.service;

import com.hungerhub.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByStreetAddressAndCityAndStateAndPincodeAndCountry(String streetAddress, String city, String state, String pincode, String country);

}
