package com.masai.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer address_id;

    @NotNull(message = "City cannot be null")
    @NotBlank(message = "City cannot be blank")
    @NotEmpty(message = "City cannot be empty")
    private String city;

    @NotNull(message = "State cannot be null")
    @NotBlank(message = "State cannot be blank")
    @NotEmpty(message = "State cannot be empty")
    private String state;

    @NotNull(message = "Phone cannot be null")
    @NotBlank(message = "Phone cannot be blank")
    @NotEmpty(message = "Phone cannot be empty")
    @Size(min=10,max=10,message="Mobile should be 10 characters.")
    @Pattern(regexp="(^$|[0-9]{10})",message="Mobile should contains only numbers")
    private String phone;

    @NotNull(message = "Pincode cannot be null")
    @NotBlank(message = "Pincode cannot be blank")
    @NotEmpty(message = "Pincode cannot be empty")
    private String pincode;

    @NotNull(message = "landmark cannot be null.")
    @NotBlank(message = "landmark status cannot be blank.")
    @NotEmpty(message = "landmark status cannot be empty.")
    @Size(min=3,max=24, message="landmark can be 3 to 24 characters.")
    private String landmark;
}
