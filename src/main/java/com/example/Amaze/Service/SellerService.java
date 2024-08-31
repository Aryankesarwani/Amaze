package com.example.Amaze.Service;

import com.example.Amaze.Dto.ResponseDto.SellerResponse;
import com.example.Amaze.Dto.ReuestDto.SellerRequest;
import org.springframework.stereotype.Service;

@Service
public interface SellerService {
     SellerResponse addSeller(SellerRequest sellerRequest);
}
