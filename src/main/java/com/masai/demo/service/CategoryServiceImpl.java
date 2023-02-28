package com.masai.demo.service;

import com.masai.demo.dto.CategoryDto;
import com.masai.demo.exception.CategoryException;
import com.masai.demo.model.Category;
import com.masai.demo.repository.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Category addCategory(CategoryDto categoryDto) throws CategoryException {

        Category category = this.dtoToCategory(categoryDto);

        if (categoryDao.save(category) == null) throw new CategoryException("Not able to Create New Category");

        return category;
    }

    @Override
    public List<Category> viewAllCategory() throws CategoryException {

        List<Category> list = this.categoryDao.findAll();

        if (list.size() == 0) throw new CategoryException("No Category Founded");

        return list;
    }

    @Override
    public Category deleteCategory(Integer catId) throws CategoryException {

        Category category = this.categoryDao.findById(catId)
                .orElseThrow(() -> new CategoryException("No Category Found with Category Id "+ catId));

        this.categoryDao.delete(category);

        return category;
    }

    @Override
    public Category updateCategoryDescription(CategoryDto categoryDto, Integer categoryId) throws CategoryException {

        Category category = this.categoryDao.findById(categoryId)
                .orElseThrow(() -> new CategoryException("No Category Found with Category Id " + categoryId));

        category.setCategoryDescription(categoryDto.getCategoryDescription());

        return this.categoryDao.save(category);

    }

    @Override
    public Category findCategory(Integer categoryId) throws CategoryException {
        return this.categoryDao.findById(categoryId)
                .orElseThrow(() -> new CategoryException("No Category Found with Category Id " + categoryId));
    }

    @Override
    public Category updateCategoryName(CategoryDto categoryDto, Integer categoryId) throws CategoryException {

        Category category = this.categoryDao.findById(categoryId)
                .orElseThrow(() -> new CategoryException("No Category Found with Category Id " + categoryId));

        category.setCategoryName(categoryDto.getCategoryName());

        return this.categoryDao.save(category);
    }

    private Category dtoToCategory(CategoryDto categoryDto){

        Category category = new Category();

        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        return category;
    }

    private CategoryDto categoryToDto(Category category){

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setCategoryDescription(category.getCategoryDescription());

        return categoryDto;
    }
}
