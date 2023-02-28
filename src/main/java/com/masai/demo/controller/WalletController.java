package com.masai.demo.controller;

import com.masai.demo.model.Wallet;
import com.masai.demo.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet/")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PutMapping("/updateWallet/{amount}/{walletId}")
    public ResponseEntity<Wallet> updateAmountHandler(@PathVariable(name = "amount") double amount, @PathVariable(name = "walletId") Integer walletId){

        Wallet wallet = this.walletService.updateAmount(amount, walletId);

        return new ResponseEntity<>(wallet, HttpStatus.ACCEPTED);

    }
}
