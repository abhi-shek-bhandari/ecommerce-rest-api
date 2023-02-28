package com.masai.demo.controller;

import com.masai.demo.exception.PromocodeException;
import com.masai.demo.model.Promocode;
import com.masai.demo.service.PromocodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/promocode/")
public class PromoCodeController {

    @Autowired
    private PromocodeService promocodeService;

    @GetMapping("/find/{promo}")
    public ResponseEntity<Promocode> findPromoCodeHandler(@PathVariable String promo){

        Promocode promocode = promocodeService.findPromocode(promo) ;

        return new ResponseEntity<Promocode>(promocode , HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<Promocode> removePromoCodeHandler(@PathVariable Integer Id) throws PromocodeException {

        Promocode promocode = promocodeService.deletePromocode(Id) ;

        return new ResponseEntity<Promocode>(promocode, HttpStatus.ACCEPTED);
    }


    @PostMapping("/add/{discount}")
    public ResponseEntity<Promocode> addPromocodeHandler(@Valid @PathVariable Integer discount) throws PromocodeException {

        Promocode promocode1 = promocodeService.createPromocode(discount) ;

        return new ResponseEntity<Promocode>(promocode1, HttpStatus.CREATED);
    }

}
