package com.masai.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @NotNull
    private String user_name;

    @Column(nullable = false)
    @Email
    private String email;

    @Min(6)
    @Max(30)
    @NotNull
    @Pattern(regexp="^[A-Z][a-z][0-9]*",message = "Password must contain min 6 characters, 1 Upper Case and 1 Lower Case and 1 Digit")
    private String password;

}
