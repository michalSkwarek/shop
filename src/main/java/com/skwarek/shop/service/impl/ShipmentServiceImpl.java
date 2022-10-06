package com.skwarek.shop.service.impl;

import com.skwarek.shop.exception.OrderNotFoundException;
import com.skwarek.shop.exception.ShipmentDuplicateException;
import com.skwarek.shop.exception.ShipmentNotFoundException;
import com.skwarek.shop.model.address.Address;
import com.skwarek.shop.model.order.Order;
import com.skwarek.shop.model.order.Shipment;
import com.skwarek.shop.model.order.ShipmentStatus;
import com.skwarek.shop.repository.AddressRepository;
import com.skwarek.shop.repository.OrderRepository;
import com.skwarek.shop.repository.ShipmentRepository;
import com.skwarek.shop.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    public List<Shipment> findAll() {
        return shipmentRepository.findAll();
    }

    @Override
    public Shipment findById(Long shipmentId) {
        return shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ShipmentNotFoundException("Not found shipment with id: " + shipmentId));
    }

    @Override
    public Shipment create(Long orderId) {
        Optional<Order> orderDb = orderRepository.findById(orderId);

        if (orderDb.isPresent()) {
            Order oldOrder = orderDb.get();

            if (oldOrder.getShipment() == null) {
                Shipment newShipment = new Shipment();
                newShipment.setShippingDate(null);
                newShipment.setTrackingNumber(null);
                newShipment.setDeliveryDate(null);
                newShipment.setShipmentStatus(ShipmentStatus.PROCESSING);
                newShipment.setOrder(oldOrder);
                newShipment.setShippingAddress(null);

                return shipmentRepository.save(newShipment);
            } else {
                throw new ShipmentDuplicateException("Duplicate shipment for order with id: " + orderId);
            }
        } else {
            throw new OrderNotFoundException("Not found order with id: " + orderId);
        }
    }

    @Override
    public Shipment addShippingAddress(Long shipmentId, Address shippingAddressRequest) {
        Optional<Shipment> shipmentDb = shipmentRepository.findById(shipmentId);

        if (shipmentDb.isPresent()) {
            Shipment oldShipment = shipmentDb.get();
            Address billingAddress = oldShipment.getOrder().getCustomer().getBillingAddress();

            Address shippingAddress = billingAddress;
            if (!billingAddress.equals(shippingAddressRequest)) {
                Address newAddress = new Address();
                newAddress.setStreetName(shippingAddressRequest.getStreetName());
                newAddress.setStreetNumber(shippingAddressRequest.getStreetNumber());
                newAddress.setDoorNumber(shippingAddressRequest.getDoorNumber());
                newAddress.setPostalCode(shippingAddressRequest.getPostalCode());
                newAddress.setCity(shippingAddressRequest.getCity());
                newAddress.setCountry(shippingAddressRequest.getCountry());

                shippingAddress = addressRepository.save(newAddress);
            }
            oldShipment.setShippingAddress(shippingAddress);

            return shipmentRepository.save(oldShipment);
        } else {
            throw new ShipmentNotFoundException("Not found shipment with id: " + shipmentId);
        }
    }

    @Override
    public Shipment startSendingParcel(Long shipmentId) {
        Optional<Shipment> shipmentDb = shipmentRepository.findById(shipmentId);

        if (shipmentDb.isPresent()) {
            Shipment oldShipment = shipmentDb.get();
            oldShipment.setShippingDate(LocalDateTime.now());
            oldShipment.setTrackingNumber("PARCELno" + oldShipment.getId());
            oldShipment.setShipmentStatus(ShipmentStatus.IN_TRANSIT);

            return shipmentRepository.save(oldShipment);
        } else {
            throw new ShipmentNotFoundException("Not found shipment with id: " + shipmentId);
        }
    }

    @Override
    public Shipment finishSendingParcel(Long shipmentId) {
        Optional<Shipment> shipmentDb = shipmentRepository.findById(shipmentId);

        if (shipmentDb.isPresent()) {
            Shipment oldShipment = shipmentDb.get();
            oldShipment.setDeliveryDate(LocalDateTime.now());
            oldShipment.setShipmentStatus(ShipmentStatus.DELIVERED);

            return shipmentRepository.save(oldShipment);
        } else {
            throw new ShipmentNotFoundException("Not found shipment with id: " + shipmentId);
        }
    }

    @Override
    public Shipment update(Long shipmentId, Shipment shipmentRequest) {
        Optional<Shipment> shipmentDb = shipmentRepository.findById(shipmentId);

        if (shipmentDb.isPresent()) {
            boolean isShipmentExists = shipmentRepository.existsByTrackingNumber(shipmentRequest.getTrackingNumber());

            if (!isShipmentExists) {
                Shipment oldShipment = shipmentDb.get();
                oldShipment.setShippingDate(shipmentRequest.getShippingDate());
                oldShipment.setTrackingNumber(shipmentRequest.getTrackingNumber());
                oldShipment.setDeliveryDate(shipmentRequest.getDeliveryDate());
                oldShipment.setShipmentStatus(shipmentRequest.getShipmentStatus());

                return shipmentRepository.save(oldShipment);
            } else {
                throw new ShipmentDuplicateException("Duplicate shipment with tracking number: " + shipmentRequest.getTrackingNumber());
            }
        } else {
            throw new ShipmentNotFoundException("Not found shipment with id: " + shipmentId);
        }
    }

    @Override
    public void deleteById(Long shipmentId) {
        boolean isShipmentExists = shipmentRepository.existsById(shipmentId);

        if (isShipmentExists) {
            shipmentRepository.deleteById(shipmentId);
        } else {
            throw new ShipmentNotFoundException("Not found shipment with id: " + shipmentId);
        }
    }

}
