package com.skwarek.shop.service;

import com.skwarek.shop.model.address.Address;

import java.util.List;

public interface AddressService {

    List<Address> findAll();

    Address findById(Long addressId);

    Address create(Address addressRequest);

    Address update(Long addressId, Address addressRequest);

    void deleteById(Long addressId);

}
