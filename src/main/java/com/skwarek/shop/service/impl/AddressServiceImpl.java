package com.skwarek.shop.service.impl;

import com.skwarek.shop.exception.AddressNotFoundException;
import com.skwarek.shop.model.address.Address;
import com.skwarek.shop.repository.AddressRepository;
import com.skwarek.shop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(Long addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("Not found address with id: " + addressId));
    }

    @Override
    public Address create(Address addressRequest) {
        Address newAddress = new Address();
        newAddress.setStreetName(addressRequest.getStreetName());
        newAddress.setStreetNumber(addressRequest.getStreetNumber());
        newAddress.setDoorNumber(addressRequest.getDoorNumber());
        newAddress.setPostalCode(addressRequest.getPostalCode());
        newAddress.setCity(addressRequest.getCity());
        newAddress.setCountry(addressRequest.getCountry());

        return addressRepository.save(newAddress);
    }

    @Override
    public Address update(Long addressId, Address addressRequest) {
        Optional<Address> addressDb = addressRepository.findById(addressId);

        if (addressDb.isPresent()) {
            Address oldAddress = addressDb.get();
            oldAddress.setStreetName(addressRequest.getStreetName());
            oldAddress.setStreetNumber(addressRequest.getStreetNumber());
            oldAddress.setDoorNumber(addressRequest.getDoorNumber());
            oldAddress.setPostalCode(addressRequest.getPostalCode());
            oldAddress.setCity(addressRequest.getCity());
            oldAddress.setCountry(addressRequest.getCountry());

            return addressRepository.save(oldAddress);
        } else {
            throw new AddressNotFoundException("Not found address with id: " + addressId);
        }
    }

    @Override
    public void deleteById(Long addressId) {
        boolean isAddressExists = addressRepository.existsById(addressId);

        if (isAddressExists) {
            addressRepository.deleteById(addressId);
        } else {
            throw new AddressNotFoundException("Not found address with id: " + addressId);
        }
    }

}
