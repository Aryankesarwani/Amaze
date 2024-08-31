package com.example.Amaze.Transformer;

import com.example.Amaze.Dto.ResponseDto.SellerResponse;
import com.example.Amaze.Dto.ReuestDto.SellerRequest;
import com.example.Amaze.Model.Seller;

public class SellerTransformer {
    public static Seller dtoToentity(SellerRequest sellerRequest){
        return Seller.builder()
                .name(sellerRequest.getName())
                .phone(sellerRequest.getPhone())
                .email(sellerRequest.getEmail())
                .build();
    }

    public static SellerResponse entityTodto(Seller seller) {
        return SellerResponse.builder().name(seller.getName())
                .phone(seller.getPhone())
                .build();
    }
}
