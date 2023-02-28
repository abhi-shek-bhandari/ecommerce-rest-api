package com.masai.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
@EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NotNull(message = "Brand name cannot be null")
    @NotBlank(message = "Brand name cannot be blank")
    @NotEmpty(message = "Brand name cannot be empty")
    private String brand;

    @Min(value = 0, message = "The value must be positive")
//    @Pattern(regexp = "^[6-9][0-9]{9}",message="Only Numbers accepted")
    private double markedPrice;

    @NotNull(message = "Product name cannot be null")
    @NotBlank(message = "Product name cannot be blank")
    @NotEmpty(message = "Product name cannot be empty")
    private String productName;

    @Min(value = 0, message = "The value must be positive")
//    @Pattern(regexp = "^[6-9][0-9]{9}",message="Only Numbers accepted")
    private double sellingPrice;

    @NotNull(message = "Image url cannot be null")
    @NotBlank(message = "Image url cannot be blank")
    @NotEmpty(message = "Image url cannot be empty")
    private String imageurl;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Category> category = new HashSet<>();

}
