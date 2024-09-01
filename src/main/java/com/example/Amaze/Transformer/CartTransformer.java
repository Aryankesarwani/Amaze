package com.example.Amaze.Transformer;

import com.example.Amaze.Dto.ResponseDto.CartResponse;
import com.example.Amaze.Dto.ResponseDto.ItemResponse;
import com.example.Amaze.Model.Cart;
import com.example.Amaze.Model.Items;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {
    public static CartResponse entityTodto(Cart cart){
        List<ItemResponse> itemResponses = new ArrayList<>();
        for(Items items : cart.getItems()){
            itemResponses.add(ItemTransformer.entityTodto(items));
        }
        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(itemResponses).build();

    }
}
