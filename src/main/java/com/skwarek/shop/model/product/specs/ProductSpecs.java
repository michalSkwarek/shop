package com.skwarek.shop.model.product.specs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skwarek.shop.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_specs")
public class ProductSpecs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "productSpecs", fetch = FetchType.LAZY)
    @JsonIgnore
    private Product product;

}
