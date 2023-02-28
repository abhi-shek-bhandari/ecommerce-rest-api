package com.masai.demo.dto;

import com.masai.demo.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String brand;

    private double markedPrice;

    private String productName;

    private double sellingPrice;

    private String imageurl;

}
