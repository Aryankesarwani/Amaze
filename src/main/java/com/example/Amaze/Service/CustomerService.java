package com.example.Amaze.Service;

import com.example.Amaze.Dto.RequestDto.CustomerRequest;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Object addCustomer(CustomerRequest customerRequest);
}
