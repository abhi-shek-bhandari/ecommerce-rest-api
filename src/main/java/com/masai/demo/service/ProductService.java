package com.masai.demo.service;

import com.masai.demo.dto.PostResponse;
import com.masai.demo.dto.ProductDto;
import com.masai.demo.exception.CategoryException;
import com.masai.demo.exception.ProductException;
import com.masai.demo.model.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(ProductDto productDto, Integer catId) throws ProductException, CategoryException;
    PostResponse viewAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) throws ProductException;
    List<Product> searchProductsByName(String name) throws ProductException;
    List<Product> searchProductsByCategory(Integer catId) throws ProductException,CategoryException;
    List<Product> searchProductsByBrand(String brand) throws ProductException;
    List<Product> searchProductsByPriceLessThan(double price) throws ProductException;

}
