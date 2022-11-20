package com.skwarek.shop.controller;

import com.skwarek.shop.model.address.Address;
import com.skwarek.shop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping(value = "/addresses")
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressService.findAll();

        if (!addresses.isEmpty()) {
            return ResponseEntity.ok(addresses);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/addresses/{addressId}")
    public ResponseEntity<Address> getAddressById(@PathVariable("addressId") Long addressId) {
        Address address = addressService.findById(addressId);

        return ResponseEntity.ok(address);
    }

    @PostMapping(value = "/addresses/create")
    public ResponseEntity<Address> createAddress(@RequestBody Address addressRequest) {
        Address createdAddress = addressService.create(addressRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/addresses")
                .path("/{addressId}").buildAndExpand(createdAddress.getId()).toUri();

        return ResponseEntity.created(location).body(createdAddress);
    }

    @PutMapping(value = "/addresses/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable("addressId") Long addressId,
                                                   @RequestBody Address addressRequest) {
        Address updatedAddress = addressService.update(addressId, addressRequest);

        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping(value = "/addresses/{addressId}")
    public ResponseEntity<HttpStatus> deleteAddressById(@PathVariable("addressId") Long addressId) {
        addressService.deleteById(addressId);

        return ResponseEntity.noContent().build();
    }

}
