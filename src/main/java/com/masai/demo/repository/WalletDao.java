package com.masai.demo.repository;

import com.masai.demo.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletDao extends JpaRepository<Wallet,Integer> {
}
