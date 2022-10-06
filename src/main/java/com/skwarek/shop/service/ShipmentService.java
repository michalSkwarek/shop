package com.skwarek.shop.service;

import com.skwarek.shop.model.address.Address;
import com.skwarek.shop.model.order.Shipment;

import java.util.List;

public interface ShipmentService {

    List<Shipment> findAll();

    Shipment findById(Long shipmentId);

    Shipment create(Long orderId);

    Shipment addShippingAddress(Long shipmentId, Address shippingAddressRequest);

    Shipment startSendingParcel(Long shipmentId);

    Shipment finishSendingParcel(Long shipmentId);

    Shipment update(Long shipmentId, Shipment shipmentRequest);

    void deleteById(Long shipmentId);

}
