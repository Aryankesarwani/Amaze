package com.example.Amaze.Controller;

import com.example.Amaze.Dto.RequestDto.OrderRequest;
import com.example.Amaze.Dto.ResponseDto.OrderResponse;
import com.example.Amaze.Exception.CustomerNotFound;
import com.example.Amaze.Exception.InsufficientQuantity;
import com.example.Amaze.Exception.InvalidCardException;
import com.example.Amaze.Exception.ProductNotFoundException;
import com.example.Amaze.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequest orderRequest) {

        try {
            OrderResponse orderResponse = orderService.placeOrder(orderRequest);
            return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

}
