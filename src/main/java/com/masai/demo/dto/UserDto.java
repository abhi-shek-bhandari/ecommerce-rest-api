package com.masai.demo.dto;

import com.masai.demo.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userFirstName;
    private String userLastName;
    private String email;
    private String password;
    private String phone;
    private Set<Address> addresses;
}
