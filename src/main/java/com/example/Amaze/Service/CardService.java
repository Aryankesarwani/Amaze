package com.example.Amaze.Service;

import com.example.Amaze.Dto.ResponseDto.CardResponse;
import com.example.Amaze.Dto.RequestDto.CardRequest;
import com.example.Amaze.Exception.CustomerNotFound;
import org.springframework.stereotype.Service;

@Service
public interface CardService {
    CardResponse addCard(CardRequest cardRequest) throws CustomerNotFound;
}
