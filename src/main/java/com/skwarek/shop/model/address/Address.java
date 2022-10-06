package com.skwarek.shop.model.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skwarek.shop.model.user.Customer;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"streetName", "streetNumber", "doorNumber", "postalCode", "city", "country"})
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "door_number")
    private String doorNumber;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "city")
    private String city;

    @Column(name = "country", columnDefinition = "ENUM('BELARUS', 'CZECHIA', 'GERMANY', 'LITHUANIA', 'POLAND', 'RUSSIA', 'SLOVAKIA', 'UKRAINE')")
    @Enumerated(EnumType.STRING)
    private Country country;

    @OneToOne(mappedBy = "billingAddress", fetch = FetchType.LAZY)
    @JsonIgnore
    private Customer customer;

}
