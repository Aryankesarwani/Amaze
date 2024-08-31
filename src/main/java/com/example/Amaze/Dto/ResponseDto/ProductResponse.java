package com.example.Amaze.Dto.ResponseDto;

import com.example.Amaze.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponse {

    String productName;

    String sellerName;

    Integer price;

    ProductStatus productStatus;

}
