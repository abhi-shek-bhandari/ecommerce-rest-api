package com.masai.demo.controller;

import com.masai.demo.dto.CategoryDto;
import com.masai.demo.model.Category;
import com.masai.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<Category> addCategoryHandler(@RequestBody CategoryDto categoryDto) {

        Category category = this.categoryService.addCategory(categoryDto);

        return new ResponseEntity<>(category, HttpStatus.CREATED);

    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<Category>> viewAllCategoryHandler(){

        List<Category> list = this.categoryService.viewAllCategory();

        return new ResponseEntity<>(list,HttpStatus.FOUND);

    }

    @DeleteMapping("/delete/{catId}")
    public ResponseEntity<Category> deleteCategoryHandler(@PathVariable Integer catId){

        Category category = this.categoryService.deleteCategory(catId);

        return new ResponseEntity<>(category,HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateDesc/{categoryId}")
    public ResponseEntity<Category> updateCategoryDescriptionHandler(@RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){

        Category category = this.categoryService.updateCategoryDescription(categoryDto, categoryId);

        return new ResponseEntity<>(category,HttpStatus.ACCEPTED);

    }

    @PutMapping("/updateName/{categoryId}")
    public ResponseEntity<Category> updateCategoryNameHandler(@RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){

        Category category = this.categoryService.updateCategoryName(categoryDto, categoryId);

        return new ResponseEntity<>(category,HttpStatus.ACCEPTED);

    }

    @GetMapping("/find/{categoryId}")
    public ResponseEntity<Category> findCategoryHandler(Integer categoryId){

        Category category = this.categoryService.findCategory(categoryId);

        return new ResponseEntity<>(category,HttpStatus.FOUND);

    }
}
