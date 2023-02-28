package com.masai.demo.controller;

import com.masai.demo.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping("/checkout")
    public ResponseEntity<Integer> checkoutHandler(@RequestParam(name = "userId") Integer userId,@RequestParam(name = "addressId") Integer addressId,@RequestParam(name = "PromoCode") String promo){

        Integer checkout = this.checkoutService.checkout(userId, addressId, promo);

        return new ResponseEntity<>(checkout, HttpStatus.ACCEPTED);
    }
}
