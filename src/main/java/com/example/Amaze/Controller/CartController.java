package com.example.Amaze.Controller;


import com.example.Amaze.Dto.RequestDto.CheckoutRequest;
import com.example.Amaze.Dto.RequestDto.ItemRequest;
import com.example.Amaze.Dto.ResponseDto.CartResponse;
import com.example.Amaze.Dto.ResponseDto.OrderResponse;
import com.example.Amaze.Exception.EmptyCartException;
import com.example.Amaze.Model.Items;
import com.example.Amaze.Service.CartService;
import com.example.Amaze.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ItemService itemService;
    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addtoCart(@RequestBody ItemRequest itemRequest){
        try{
            Items item = itemService.createItem(itemRequest);
            CartResponse cartResponse = cartService.addtoCart(item,itemRequest);
            return new ResponseEntity(cartResponse,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout")// solve deletion of product quantity bug in checkout order
    public ResponseEntity checkoutCart(@RequestBody CheckoutRequest checkoutRequest){
        try {
            OrderResponse orderResponse = cartService.checkoutCart(checkoutRequest);
            return new ResponseEntity(orderResponse,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
