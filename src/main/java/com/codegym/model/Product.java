package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @Min(0)
    private Float price;
    @Min(0)
    private int quantity;
    private LocalDate dateCreate;
    @NotEmpty
    private String description;

    @ManyToOne
    @JoinColumn(name="productType_id")
    private ProductType productType;

    public Product() {
    }

    public Product(Long id, String name, Float price, int quantity, LocalDate dateCreate, String description, ProductType productType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.dateCreate = dateCreate;
        this.description = description;
        this.productType = productType;
    }

    public Product(String name, Float price, int quantity, LocalDate dateCreate, String description, ProductType productType) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.dateCreate = dateCreate;
        this.description = description;
        this.productType = productType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
