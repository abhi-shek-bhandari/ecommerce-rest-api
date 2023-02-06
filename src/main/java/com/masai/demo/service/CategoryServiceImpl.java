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
    public List<CategoryDto> viewAllCategory() throws CategoryException {

        List<Category> list = this.categoryDao.findAll();

        if (list.size() == 0) throw new CategoryException("No Category Founded");

        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Category category: list) {
            categoryDtos.add(this.categoryToDto(category));
        }

        return categoryDtos;
    }

    @Override
    public Category deleteCategory(Integer catId) throws CategoryException {

        Category category = this.categoryDao.findById(catId)
                .orElseThrow(() -> new CategoryException("No Category Found with Category Id "+ catId));

        this.categoryDao.delete(category);

        return category;
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
