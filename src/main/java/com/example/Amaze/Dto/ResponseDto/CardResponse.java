package com.example.Amaze.Dto.ResponseDto;

import com.example.Amaze.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponse {

    String name;
    Long cardNumber;
    CardType cardType;
}
