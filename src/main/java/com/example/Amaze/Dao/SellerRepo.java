package com.example.Amaze.Dao;

import com.example.Amaze.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository<Seller,Integer> {
    Seller findByEmail(String sellerEmail);
}
