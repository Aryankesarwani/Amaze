package com.example.Amaze.Service;

import com.example.Amaze.Dto.RequestDto.CheckoutRequest;
import com.example.Amaze.Dto.RequestDto.ItemRequest;
import com.example.Amaze.Dto.ResponseDto.CartResponse;
import com.example.Amaze.Dto.ResponseDto.OrderResponse;
import com.example.Amaze.Exception.*;
import com.example.Amaze.Model.Cart;
import com.example.Amaze.Model.Customer;
import com.example.Amaze.Model.Items;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    Cart generateCart(Customer customer);

    CartResponse addtoCart(Items item, ItemRequest itemRequest) throws ProductNotFoundException;

    OrderResponse checkoutCart(CheckoutRequest checkoutRequest) throws CustomerNotFound, InvalidCardException, EmptyCartException, InsufficientQuantity;
}
