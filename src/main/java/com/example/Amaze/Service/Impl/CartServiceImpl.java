package com.example.Amaze.Service.Impl;

import com.example.Amaze.Model.Cart;
import com.example.Amaze.Model.Customer;
import com.example.Amaze.Service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Override
    public Cart generateCart(Customer customer) {
        Cart cart = Cart.builder().cartTotal(0).customer(customer).build();
        return cart;
    }
}
