package com.masai.demo.service;

import com.masai.demo.exception.CartException;
import com.masai.demo.exception.PromocodeException;
import com.masai.demo.exception.UserException;
import com.masai.demo.exception.WalletException;

public interface CheckoutService {
    Integer checkout(Integer userId, Integer addressId, String promo)throws UserException, CartException, WalletException, PromocodeException;
}
