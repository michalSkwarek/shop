package com.skwarek.shop.controller;

import com.skwarek.shop.model.address.Address;
import com.skwarek.shop.model.order.Shipment;
import com.skwarek.shop.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @GetMapping(value = "/shipments")
    public ResponseEntity<List<Shipment>> getAllShipments() {
        List<Shipment> shipments = shipmentService.findAll();

        if (!shipments.isEmpty()) {
            return ResponseEntity.ok(shipments);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/shipments/{shipmentId}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable("shipmentId") Long shipmentId) {
        Shipment shipment = shipmentService.findById(shipmentId);

        return ResponseEntity.ok(shipment);
    }

    @PostMapping(value = "/orders/{orderId}/shipments/create")
    public ResponseEntity<Shipment> createShipment(@PathVariable("orderId") Long orderId) {
        Shipment createdShipment = shipmentService.create(orderId);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("/shipments")
                .path("/{shipmentId}").buildAndExpand(createdShipment.getId()).toUri();

        return ResponseEntity.created(location).body(createdShipment);
    }

    @PutMapping(value = "shipments/{shipmentId}/address")
    public ResponseEntity<Shipment> addShippingAddress(@PathVariable("shipmentId") Long shipmentId,
                                                       @RequestBody Address shippingAddressRequest) {
        Shipment updatedShipment = shipmentService.addShippingAddress(shipmentId, shippingAddressRequest);

        return ResponseEntity.ok(updatedShipment);
    }

    @PutMapping(value = "shipments/{shipmentId}/send")
    public ResponseEntity<Shipment> startSendingParcel(@PathVariable("shipmentId") Long shipmentId) {
        Shipment updatedShipment = shipmentService.startSendingParcel(shipmentId);

        return ResponseEntity.ok(updatedShipment);
    }

    @PutMapping(value = "shipments/{shipmentId}/delivery")
    public ResponseEntity<Shipment> finishSendingParcel(@PathVariable("shipmentId") Long shipmentId) {
        Shipment updatedShipment = shipmentService.finishSendingParcel(shipmentId);

        return ResponseEntity.ok(updatedShipment);
    }

    @PutMapping(value = "/shipments/{shipmentId}")
    public ResponseEntity<Shipment> updateShipment(@PathVariable("shipmentId") Long shipmentId,
                                                   @RequestBody Shipment shipmentRequest) {
        Shipment updatedShipment = shipmentService.update(shipmentId, shipmentRequest);

        return ResponseEntity.ok(updatedShipment);
    }

    @DeleteMapping(value = "/shipments/{shipmentId}")
    public ResponseEntity<HttpStatus> deleteShipmentById(@PathVariable("shipmentId") Long shipmentId) {
        shipmentService.deleteById(shipmentId);

        return ResponseEntity.noContent().build();
    }

}
