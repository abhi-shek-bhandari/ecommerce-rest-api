package com.masai.demo.repository;

import com.masai.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM Product p WHERE p.productName LIKE :name")
    List<Product> findProductName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.brand LIKE :name")
    List<Product> findProductsByName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.sellingPrice <= :price")
    List<Product> ProductsByPriceLessThan(@Param("price") double price);
}
