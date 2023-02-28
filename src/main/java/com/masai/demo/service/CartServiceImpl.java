package com.masai.demo.service;

import com.masai.demo.dto.CartDto;
import com.masai.demo.dto.ProductDto;
import com.masai.demo.exception.CartException;
import com.masai.demo.exception.ProductException;
import com.masai.demo.exception.UserException;
import com.masai.demo.model.Cart;
import com.masai.demo.model.Product;
import com.masai.demo.model.User;
import com.masai.demo.repository.CartDao;
import com.masai.demo.repository.ProductDao;
import com.masai.demo.repository.UserDao;
import org.hibernate.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CartDao cartDao;

    @Override
    public List<Product> addProductInCart(Integer cartId, Integer productId) throws ProductException, CartException {

        Cart cart = this.cartDao.findById(cartId).orElseThrow(() -> new CartException("No Cart Found"));

        Product product = this.productDao.findById(productId).orElseThrow(() -> new ProductException("No Product Found with Product ID "+ productId));

        cart.getProducts().add(product);

        cart.setTotalAmount(cart.getTotalAmount()+product.getSellingPrice());

        this.cartDao.save(cart);

        return new ArrayList<>(cart.getProducts());
    }

    @Override
    public Cart removeFromCart(Integer cartid, Integer productId) throws ProductException, CartException {

        Cart cart = this.cartDao.findById(cartid).orElseThrow(() -> new CartException("No Cart Found"));

        Product product = this.productDao.findById(productId).orElseThrow(() -> new ProductException("No Product Found with Product ID "+ productId));

        cart.setTotalAmount(cart.getTotalAmount()-product.getSellingPrice());

        cart.getProducts().remove(product);

        return this.cartDao.save(cart);
    }

    @Override
    public Cart removeAllFromCart(Integer cartid) throws CartException {

        Cart cart = this.cartDao.findById(cartid).orElseThrow(() -> new CartException("No Cart Found"));

        cart.getProducts().clear();

        cart.setTotalAmount(0);

        this.cartDao.save(cart);

        return cart;
    }

}