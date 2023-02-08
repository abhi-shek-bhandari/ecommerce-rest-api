package com.masai.demo.repository;

import com.masai.demo.model.Promocode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocodeDao extends JpaRepository<Promocode,Integer>{
    Promocode findByPromocode(String promo);
}
