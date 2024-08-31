package com.example.Amaze.Service;

import com.example.Amaze.Dto.ReuestDto.CustomerRequest;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Object addCustomer(CustomerRequest customerRequest);
}
