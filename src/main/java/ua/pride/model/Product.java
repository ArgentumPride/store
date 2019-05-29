package ua.pride.model;

import java.sql.Blob;

public class Product {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private Blob picture;

    public Product() {
    }

    public Product(Long id) {
        super();
        this.id = id;
    }

    public Product(Long id, String name, String description, Long price, Blob picture) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.picture = picture;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }
}
