package com.example.Amaze.Controller;

import com.example.Amaze.Dto.ResponseDto.SellerResponse;
import com.example.Amaze.Dto.RequestDto.SellerRequest;
import com.example.Amaze.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;
    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequest sellerRequest){
        SellerResponse sellerResponse = sellerService.addSeller(sellerRequest);
        return new ResponseEntity(sellerResponse, HttpStatus.CREATED);
    }

}
