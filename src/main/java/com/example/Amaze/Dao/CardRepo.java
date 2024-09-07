package com.example.Amaze.Dao;

import com.example.Amaze.Model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepo extends JpaRepository<Cards,Integer> {
    Cards findBycardNumber(String cardNo);
}
