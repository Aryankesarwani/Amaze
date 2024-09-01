package com.example.Amaze.Service.Impl;

import com.example.Amaze.Dao.CustomerRepo;
import com.example.Amaze.Dto.ResponseDto.CardResponse;
import com.example.Amaze.Dto.RequestDto.CardRequest;
import com.example.Amaze.Exception.CustomerNotFound;
import com.example.Amaze.Model.Cards;
import com.example.Amaze.Model.Customer;
import com.example.Amaze.Service.CardService;
import com.example.Amaze.Transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CustomerRepo customerRepo;
    @Override
    public CardResponse addCard(CardRequest cardRequest) throws CustomerNotFound {
        Customer customer = customerRepo.findByEmail(cardRequest.getEmail());
        if(customer == null) {
            throw new CustomerNotFound("Invalid email...!!");
        }
        Cards cards = CardTransformer.dtoToentity(cardRequest);
        cards.setCustomer(customer);

        customer.getCards().add(cards);

        //saving both card and customer
        Customer savedCustomer = customerRepo.save(customer);

        return CardTransformer.entityTodto(cards);

    }
}
