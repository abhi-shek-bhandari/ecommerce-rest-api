package com.masai.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Promocode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer promocodeId;

    private String promocode;

//    @Pattern(regexp = "^[6-9][0-9]{9}",message="Only Numbers accepted")
    @Min(value = 0L, message = "The value must be positive")
    private Integer discountPercentage;
}
