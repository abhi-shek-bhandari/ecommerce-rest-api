package com.masai.demo.service;

import com.masai.demo.exception.WalletException;
import com.masai.demo.model.Wallet;
import com.masai.demo.repository.WalletDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    private WalletDao walletDao;

    @Override
    public Wallet updateAmount(double amount, Integer walletId) throws WalletException {

        Wallet wallet = this.walletDao.findById(walletId)
                .orElseThrow(() -> new WalletException("No Wallet found with Wallet Id: " + walletId));

        wallet.setAmount(wallet.getAmount() + amount);

        return this.walletDao.save(wallet);

    }
}
