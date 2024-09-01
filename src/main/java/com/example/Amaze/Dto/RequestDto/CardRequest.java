package com.example.Amaze.Dto.RequestDto;

import com.example.Amaze.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardRequest {

    String email;

    Long cardNumber;

    CardType cardType;

    Date expiry;

    int cvv;
}
