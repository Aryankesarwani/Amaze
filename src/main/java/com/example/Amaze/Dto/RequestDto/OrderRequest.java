package com.example.Amaze.Dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderRequest {

    String email;

    Integer productId;

    String cardNo ;

    int cvv;

    int requiredQuantity;

}
