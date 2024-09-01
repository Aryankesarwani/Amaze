package com.example.Amaze.Controller;

import com.example.Amaze.Dto.RequestDto.CustomerRequest;
import com.example.Amaze.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

        @Autowired
        CustomerService customerService;

        @PostMapping("/add")
        public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest){
            return new ResponseEntity(customerService.addCustomer(customerRequest), HttpStatus.CREATED);
        }
}
