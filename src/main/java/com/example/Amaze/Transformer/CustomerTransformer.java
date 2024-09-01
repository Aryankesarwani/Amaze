package com.example.Amaze.Transformer;

import com.example.Amaze.Dto.ResponseDto.CustomerResponse;
import com.example.Amaze.Dto.RequestDto.CustomerRequest;
import com.example.Amaze.Model.Customer;

public class CustomerTransformer {
    public static Customer dtoToentity(CustomerRequest customerRequest) {
        return Customer.builder().name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .phone(customerRequest.getPhone())
                .addr(customerRequest.getAddr())
                .gender(customerRequest.getGender()).build();

    }

    public static CustomerResponse entityTodto(Customer customer) {
        return CustomerResponse.builder().name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone()).build();
    }
}
