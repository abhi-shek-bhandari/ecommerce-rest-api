package com.masai.demo.service;

import com.masai.demo.dto.CartDto;
import com.masai.demo.dto.ProductDto;
import com.masai.demo.exception.CartException;
import com.masai.demo.exception.ProductException;
import com.masai.demo.model.Cart;
import com.masai.demo.model.Product;
import org.hibernate.cache.CacheException;

import java.util.List;

public interface CartService {

    List<Product> addProductInCart(Integer cartId, Integer productId) throws ProductException, CartException;
    Cart removeFromCart(Integer cartid, Integer productId) throws ProductException, CartException;
    Cart removeAllFromCart(Integer cartid) throws CartException;

}
