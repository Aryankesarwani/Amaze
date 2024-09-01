package com.example.Amaze.Controller;

import com.example.Amaze.Dto.RequestDto.CardRequest;
import com.example.Amaze.Dto.ResponseDto.CardResponse;
import com.example.Amaze.Exception.CustomerNotFound;
import com.example.Amaze.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity<CardResponse> addCard(@RequestBody CardRequest cardRequest){
        try {
            return  new ResponseEntity<>(cardService.addCard(cardRequest), HttpStatus.ACCEPTED);
        } catch (CustomerNotFound e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
