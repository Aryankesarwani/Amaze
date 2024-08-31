package com.example.Amaze.Service.Impl;

import com.example.Amaze.Dao.CustomerRepo;
import com.example.Amaze.Dto.ResponseDto.CustomerResponse;
import com.example.Amaze.Dto.ReuestDto.CustomerRequest;
import com.example.Amaze.Model.Cart;
import com.example.Amaze.Model.Customer;
import com.example.Amaze.Service.CartService;
import com.example.Amaze.Transformer.CustomerTransformer;
import com.example.Amaze.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CartService cartService;
    @Autowired
    CustomerRepo customerRepo;

    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        //dto to entity
        Customer customer = CustomerTransformer.dtoToentity(customerRequest);
        Cart cart = cartService.generateCart(customer);
        customer.setCart(cart);
        customerRepo.save(customer);//save both customer and cart.

        //entity to dto
        CustomerResponse customerResponse = CustomerTransformer.entityTodto(customer);
        return customerResponse;
    }
}
