package com.example.Amaze.Dao;

import com.example.Amaze.Model.Cart;
import com.example.Amaze.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity,Integer> {
}
