package com.masai.demo.service;

import com.masai.demo.dto.ProductDto;
import com.masai.demo.exception.CategoryException;
import com.masai.demo.exception.ProductException;
import com.masai.demo.model.Category;
import com.masai.demo.model.Product;
import com.masai.demo.repository.CategoryDao;
import com.masai.demo.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Product addProduct(ProductDto productDto, Integer catId) throws ProductException, CategoryException {

        Product product = this.dtoToProduct(productDto);

        Category category = this.categoryDao.findById(catId).orElseThrow(() -> new CategoryException("No Category Found with Category id "+catId));

        category.getProducts().add(product);

        product.getCategory().add(category);

        if (this.productDao.save(product) == null) {
            throw new ProductException("Product was not save");
        }

        return product;
    }

    @Override
    public List<ProductDto> viewAllProducts() throws ProductException {

        List<Product> products = this.productDao.findAll();

        if (products.isEmpty()) throw new ProductException("No Product Found");

        List<ProductDto> dtoList = new ArrayList<>();

        for (Product product : products){
            dtoList.add(this.productToDto(product));
        }

        return dtoList;
    }

    @Override
    public List<ProductDto> searchProductsByName(String name) throws ProductException {

        List<Product> productsByName = this.productDao.findProductName("%" + name + "%");

        if (productsByName.isEmpty()) throw new ProductException("No Product Found");

        List<ProductDto> dtoList = new ArrayList<>();

        for (Product product : productsByName){
            dtoList.add(this.productToDto(product));
        }

        return dtoList;
    }

    @Override
    public List<Product> searchProductsByCategory(Integer catId) throws ProductException, CategoryException {
        Category category = this.categoryDao.findById(catId).orElseThrow(() -> new CategoryException("No Category Found with Category id " + catId));

        Set<Product> products = category.getProducts();

        if (products.isEmpty()) throw new ProductException("No Products Found");

        return new ArrayList<>(products);
    }

    @Override
    public List<Product> searchProductsByBrand(String brand) throws ProductException {

        List<Product> productsByName = this.productDao.findProductsByName("%" + brand + "%");

        if (productsByName.isEmpty()) throw new ProductException("No Products Found");

        return productsByName;
    }

    @Override
    public List<Product> searchProductsByPriceLessThan(double price) throws ProductException {

        List<Product> products = this.productDao.ProductsByPriceLessThan(price);

        if (products.isEmpty()) throw new ProductException("No Products Found");

        return products;

    }

    private Product dtoToProduct(ProductDto productDto){

        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setSellingPrice(productDto.getSellingPrice());
        product.setBrand(productDto.getBrand());
        product.setMarkedPrice(productDto.getMarkedPrice());
        product.setCategory(productDto.getCategory());

        return product;
    }

    private ProductDto productToDto(Product product){

        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.getProductName());
        productDto.setBrand(product.getBrand());
        productDto.setCategory(product.getCategory());
        productDto.setMarkedPrice(product.getMarkedPrice());
        productDto.setSellingPrice(product.getSellingPrice());

        return productDto;
    }
}
