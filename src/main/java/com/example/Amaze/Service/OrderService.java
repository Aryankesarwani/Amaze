package com.example.Amaze.Service;

import com.example.Amaze.Dto.RequestDto.OrderRequest;
import com.example.Amaze.Dto.ResponseDto.OrderResponse;
import com.example.Amaze.Exception.CustomerNotFound;
import com.example.Amaze.Exception.InsufficientQuantity;
import com.example.Amaze.Exception.InvalidCardException;
import com.example.Amaze.Exception.ProductNotFoundException;
import com.example.Amaze.Model.Cards;
import com.example.Amaze.Model.Cart;
import com.example.Amaze.Model.OrderEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest) throws CustomerNotFound, ProductNotFoundException, InsufficientQuantity, InvalidCardException;

    OrderEntity placeOrder(Cart cart, Cards card) throws InsufficientQuantity;
}
