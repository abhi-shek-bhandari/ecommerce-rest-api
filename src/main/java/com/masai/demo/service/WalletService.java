package com.masai.demo.service;

import com.masai.demo.exception.WalletException;
import com.masai.demo.model.Wallet;

public interface WalletService {
    Wallet updateAmount(double amount, Integer walletId)throws WalletException;
}
