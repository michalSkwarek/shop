package com.skwarek.shop.model.order;

import com.skwarek.shop.model.address.Address;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipment")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shipping_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime shippingDate;

    @Column(name = "tracking_number", unique = true)
    private String trackingNumber;

    @Column(name = "delivery_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime deliveryDate;

    @Column(name = "shipment_status", columnDefinition = "ENUM('PROCESSING', 'IN_TRANSIT', 'DELIVERED')")
    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shipping_address_id")
    private Address shippingAddress;

}
