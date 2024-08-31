package com.example.Amaze.Service.Impl;

import com.example.Amaze.Dao.SellerRepo;
import com.example.Amaze.Dto.ResponseDto.SellerResponse;
import com.example.Amaze.Dto.ReuestDto.SellerRequest;
import com.example.Amaze.Model.Seller;
import com.example.Amaze.Service.SellerService;
import com.example.Amaze.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepo sellerRepo;
    @Override
    public SellerResponse addSeller(SellerRequest sellerRequest) {
        Seller seller = SellerTransformer.dtoToentity(sellerRequest);
        Seller savedSeller = sellerRepo.save(seller);
        return SellerTransformer.entityTodto(savedSeller);
    }
}
