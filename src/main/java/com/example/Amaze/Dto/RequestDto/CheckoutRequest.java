package com.example.Amaze.Dto.RequestDto;

import com.example.Amaze.Dto.ResponseDto.OrderResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CheckoutRequest {

    String email;

    String cardNumber;

    int cvv;

}
