package com.masai.demo.service;

import com.masai.demo.exception.PromocodeException;
import com.masai.demo.model.Promocode;

public interface PromocodeService {

    Promocode createPromocode(Integer discountPercent)throws PromocodeException;
    Promocode deletePromocode(Integer promoId)throws PromocodeException;
    Promocode findPromocode(String promo)throws PromocodeException;
}
