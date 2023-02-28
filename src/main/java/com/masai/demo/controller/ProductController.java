package com.masai.demo.controller;

import com.masai.demo.dto.PostResponse;
import com.masai.demo.dto.ProductDto;
import com.masai.demo.model.Product;
import com.masai.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add/{catId}")
    public ResponseEntity<Product> addProductHandler(@Valid @RequestBody ProductDto productDto, @PathVariable Integer catId){

        Product product = this.productService.addProduct(productDto,catId);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/viewAll")
    public ResponseEntity<PostResponse> viewAllProductsHandler(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sortBy",required = false,defaultValue = "productId")String sortBy,
            @RequestParam(value = "sortDir",required = false,defaultValue = "asc")String sortDir){

        PostResponse products = this.productService.viewAllProducts(pageNumber,pageSize,sortBy,sortDir);

        return new ResponseEntity<>(products,HttpStatus.FOUND);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<Product>> searchProductsByNameHandler(@PathVariable String name){

        List<Product> products = this.productService.searchProductsByName(name);

        return new ResponseEntity<>(products,HttpStatus.FOUND);
    }

    @GetMapping("/findByCategory/{catId}")
    public ResponseEntity<List<Product>> searchProductsByCategoryHandler(@PathVariable Integer catId){

        List<Product> products = this.productService.searchProductsByCategory(catId);

        return new ResponseEntity<>(products,HttpStatus.FOUND);
    }

    @GetMapping("/findByBrand/{brand}")
    public ResponseEntity<List<Product>> searchProductsByBrand(@PathVariable String brand){

        List<Product> products = this.productService.searchProductsByBrand(brand);

        return new ResponseEntity<>(products,HttpStatus.FOUND);

    }

    @GetMapping("/findByPrice/{price}")
    public ResponseEntity<List<Product>> searchProductsByPriceLessThanHandler(@PathVariable double price){

        List<Product> products = this.productService.searchProductsByPriceLessThan(price);

        return new ResponseEntity<>(products,HttpStatus.FOUND);
    }
}
