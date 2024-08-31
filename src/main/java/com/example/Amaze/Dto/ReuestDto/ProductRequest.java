package com.example.Amaze.Dto.ReuestDto;

import com.example.Amaze.Enum.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequest {

    String sellerEmail;

    String productName;

    Integer quantity;

    Integer price;

    Category category;

}
