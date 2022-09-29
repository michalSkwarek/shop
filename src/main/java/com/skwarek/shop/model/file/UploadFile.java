package com.skwarek.shop.model.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skwarek.shop.model.product.Company;
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
@Table(name = "upload_file")
public class UploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name", unique = true)
    private String fileName;

    @Column(name = "data")
    private byte[] data;

    @OneToOne(mappedBy = "picture", fetch = FetchType.LAZY)
    @JsonIgnore
    private Product product;

    @OneToOne(mappedBy = "logo", fetch = FetchType.LAZY)
    @JsonIgnore
    private Company company;

}
