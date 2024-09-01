package com.example.Amaze.Transformer;

import com.example.Amaze.Dto.RequestDto.ItemRequest;
import com.example.Amaze.Dto.ResponseDto.ItemResponse;
import com.example.Amaze.Model.Customer;
import com.example.Amaze.Model.Items;
import com.example.Amaze.Model.Product;

import java.util.List;

public class ItemTransformer {

    public static Items entityToitem(int quantity) {
        return Items.builder()
                .requiredQuantity(quantity)
                .build();
    }

    public static ItemResponse entityTodto(Items items) {
        return ItemResponse.builder()
                .productName(items.getProduct().getName())
                .price(items.getProduct().getPrice())
                .quantity(items.getRequiredQuantity())
                .build();
    }
}
