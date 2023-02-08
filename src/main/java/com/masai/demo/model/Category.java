package com.masai.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @NotNull(message = "Category Name cannot be null")
    @NotBlank(message = "Category Name cannot be blank")
    @NotEmpty(message = "Category Name cannot be empty")
    private String categoryName;

    private String categoryDescription;

    @ManyToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(getCategoryId(), category.getCategoryId()) && Objects.equals(getCategoryName(), category.getCategoryName()) && Objects.equals(getCategoryDescription(), category.getCategoryDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryId(), getCategoryName(), getCategoryDescription());
    }
}
