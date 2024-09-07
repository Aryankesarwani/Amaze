package com.example.Amaze.Transformer;

import com.example.Amaze.Dto.RequestDto.OrderRequest;
import com.example.Amaze.Dto.ResponseDto.ItemResponse;
import com.example.Amaze.Dto.ResponseDto.OrderResponse;
import com.example.Amaze.Model.Customer;
import com.example.Amaze.Model.Items;
import com.example.Amaze.Model.OrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {
    public static OrderEntity dtoTOentity(Items item, Customer customer) {
        return OrderEntity.builder()
                .OrderNo(UUID.randomUUID().toString())
                .customer(customer)
                .items(new ArrayList<>())
                .totalValue(item.getRequiredQuantity()*item.getProduct().getPrice())
                .build();
    }

    public static OrderResponse entityTodto(OrderEntity savedOrder) {

        List<ItemResponse> itemResponses = new ArrayList<>();
        for(Items item : savedOrder.getItems()){
            itemResponses.add(ItemTransformer.entityTodto(item));
        }
        return OrderResponse.builder()
                .orderDate(savedOrder.getOrderDate())
                .cardUsed(savedOrder.getCardUsed())
                .customerName(savedOrder.getCustomer().getName())
                .totalValue(savedOrder.getTotalValue())
                .OrderNumber(savedOrder.getOrderNo())
                .items(itemResponses)
                .build();
    }
}
