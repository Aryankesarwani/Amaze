package com.example.Amaze.Transformer;

import com.example.Amaze.Dto.ResponseDto.CardResponse;
import com.example.Amaze.Dto.RequestDto.CardRequest;
import com.example.Amaze.Model.Cards;

public class CardTransformer {
    public static Cards dtoToentity(CardRequest cardRequest) {
        return Cards.builder()
                .cardType(cardRequest.getCardType())
                .cardNumber(cardRequest.getCardNumber())
                .cvv(cardRequest.getCvv())
                .expiry(cardRequest.getExpiry())
                .build();
    }

    public static CardResponse entityTodto(Cards cards) {

        return CardResponse.builder()
                .name(cards.getCustomer().getName())
                .cardNumber(cards.getCardNumber())
                .cardType(cards.getCardType()).build();
    }
}
