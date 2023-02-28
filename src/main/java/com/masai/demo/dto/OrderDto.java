package com.masai.demo.dto;

import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String firstName;

    private String lastName;

    private double orderAmount;

    private String city;

    private String state;

    private String phone;

    private String pincode;

    private String landmark;

    private Boolean orderCancelled;

    private String promoCode;

    private LocalDateTime orderTimeCreatedOn;

    private Map<String, Double> products = new HashMap<>();
}
