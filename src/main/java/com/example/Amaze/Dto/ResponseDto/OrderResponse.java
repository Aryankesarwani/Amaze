package com.example.Amaze.Dto.ResponseDto;

import com.example.Amaze.Model.Items;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponse {

    String customerName;

    String OrderNumber;

    int totalValue;

    String cardUsed;

    Date orderDate;

    List<ItemResponse> items;

}
