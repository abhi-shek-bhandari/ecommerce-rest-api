package com.masai.demo.model;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String email;

    private String password;

}
