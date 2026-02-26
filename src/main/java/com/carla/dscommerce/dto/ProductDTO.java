package com.carla.dscommerce.dto;

import com.carla.dscommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {

    private Long id;

    @Size(min = 3, max = 80, message = "The Name field must be between 3 and 80 characters long.")
    @NotBlank(message = "Field required.")
    private String name;

    @Size(min = 10, message = "The Description field must have at least 10 characters.")
    @NotBlank
    private String description;

    @Positive(message = "The Price field should be a positive number.")
    private Double price;
    private String imgUrl;

    public ProductDTO(){
    }

    public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public ProductDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
