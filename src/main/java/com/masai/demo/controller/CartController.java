package com.masai.demo.controller;

import com.masai.demo.model.Cart;
import com.masai.demo.model.Product;
import com.masai.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    private CartService cartService;

    @PutMapping("/addProduct")
    public ResponseEntity<List<Product>> addProductInCart(@RequestParam(name = "cartId") Integer cartId,@RequestParam(name = "productId") Integer productId){

        List<Product> products = this.cartService.addProductInCart(cartId, productId);

        return new ResponseEntity<>(products, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Cart> removeFromCart(@RequestParam(name = "cartId") Integer cartid,@RequestParam(name = "productId") Integer productId){

        Cart cart = this.cartService.removeFromCart(cartid, productId);

        return new ResponseEntity<>(cart,HttpStatus.OK);
    }

    @DeleteMapping("/removeAll")
    public ResponseEntity<Cart> removeAllFromCart(@RequestParam(name = "cartId") Integer cartid){

        Cart cart = this.cartService.removeAllFromCart(cartid);

        return new ResponseEntity<>(cart,HttpStatus.ACCEPTED);
    }
}
