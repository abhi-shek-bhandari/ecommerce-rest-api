package com.masai.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    @NotNull(message = "Category Name cannot be null")
    @NotBlank(message = "Category Name cannot be blank")
    @NotEmpty(message = "Category Name cannot be empty")
    private String categoryName;

    private String categoryDescription;

}
