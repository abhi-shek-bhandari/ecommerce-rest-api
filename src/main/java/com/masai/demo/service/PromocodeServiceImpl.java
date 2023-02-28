package com.masai.demo.service;

import com.masai.demo.exception.ProductException;
import com.masai.demo.exception.PromocodeException;
import com.masai.demo.model.Promocode;
import com.masai.demo.repository.PromocodeDao;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class PromocodeServiceImpl implements PromocodeService{

    @Autowired
    private PromocodeDao promocodeDao;

    @Override
    public Promocode createPromocode(Integer discountPercent) throws PromocodeException {

        Promocode promocode = new Promocode();
        promocode.setDiscountPercentage(discountPercent);
        promocode.setPromocode(this.generatingRandomAlphanumericString());


        return this.promocodeDao.save(promocode);
    }

    @Override
    public Promocode deletePromocode(Integer promoId) throws PromocodeException {

        Promocode promocode = this.promocodeDao.findById(promoId).orElseThrow(() -> new PromocodeException("No Promocode founded by Promo Id: "+ promoId));

        this.promocodeDao.delete(promocode);

        return promocode;
    }

    @Override
    public Promocode findPromocode(String promo) throws PromocodeException {

        Promocode promocode = this.promocodeDao.findByPromocode(promo);

        if (promo == null) throw new PromocodeException("Invalid Promocode Found!");

        return promocode;
    }


    private String generatingRandomAlphanumericString() {

        String generatedString = RandomString.make(9);
//        System.out.println(generatedString);

        return generatedString;
    }

}
