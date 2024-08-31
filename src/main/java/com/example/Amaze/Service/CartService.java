package com.example.Amaze.Service;

import com.example.Amaze.Model.Cart;
import com.example.Amaze.Model.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    Cart generateCart(Customer customer);
}
