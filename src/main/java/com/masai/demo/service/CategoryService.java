package com.masai.demo.service;

import com.masai.demo.dto.CategoryDto;
import com.masai.demo.exception.CategoryException;
import com.masai.demo.model.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(CategoryDto categoryDto)throws CategoryException;
    List<Category> viewAllCategory()throws CategoryException;
    Category deleteCategory(Integer catId)throws CategoryException;

}
