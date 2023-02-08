package com.masai.demo.dto;

import com.masai.demo.model.Address;
import com.masai.demo.model.Product;
import com.masai.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private double totalAmount;

    private User user;

    private Set<Product> products = new HashSet<>();

    private Address address;
}
