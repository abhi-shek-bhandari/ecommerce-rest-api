package com.masai.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @NotNull(message = "First Name cannot be null.")
    @NotBlank(message = "First Name cannot be blank.")
    @NotEmpty(message = "First Name cannot be empty.")
    private String userFirstName;

    @NotNull(message = "Last Name cannot be null.")
    @NotBlank(message = "Last Name cannot be blank.")
    @NotEmpty(message = "Last Name cannot be empty.")
    private String userLastName;

    @Email
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Min(6)
    @Max(30)
    @NotNull
    @Pattern(regexp="^[A-Z][a-z][0-9]*",message = "Password must contain min 6 characters, 1 Upper Case and 1 Lower Case and 1 Digit")
    private String password;

    @NotNull(message = "Phone cannot be null")
    @NotBlank(message = "Phone cannot be blank")
    @NotEmpty(message = "Phone cannot be empty")
    @Size(min=10,max=10,message="Mobile should be 10 characters.")
    @Pattern(regexp="(^$|[0-9]{10})",message="Mobile should contains only numbers")
    private String phone;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Address> addresses;
}
