package com.masai.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NotNull(message = "Brand name cannot be null")
    @NotBlank(message = "Brand name cannot be blank")
    @NotEmpty(message = "Brand name cannot be empty")
    private String brand;

    @Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="Only Numbers accepted")
    @Min(value = 0L, message = "The value must be positive")
    private double markedPrice;

    @NotNull(message = "Product name cannot be null")
    @NotBlank(message = "Product name cannot be blank")
    @NotEmpty(message = "Product name cannot be empty")
    private String productName;

    @Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="Only Numbers accepted")
    @Min(value = 0L, message = "The value must be positive")
    private double sellingPrice;

    @NotNull(message = "Image url cannot be null")
    @NotBlank(message = "Image url cannot be blank")
    @NotEmpty(message = "Image url cannot be empty")
    private String imageurl;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Category> category = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.getMarkedPrice(), getMarkedPrice()) == 0 && Double.compare(product.getSellingPrice(), getSellingPrice()) == 0 && Objects.equals(getProductId(), product.getProductId()) && Objects.equals(getBrand(), product.getBrand()) && Objects.equals(getProductName(), product.getProductName()) && Objects.equals(getImageurl(), product.getImageurl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getBrand(), getMarkedPrice(), getProductName(), getSellingPrice(), getImageurl());
    }
}
