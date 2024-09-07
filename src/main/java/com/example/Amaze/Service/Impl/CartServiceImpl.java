package com.example.Amaze.Service.Impl;

import com.example.Amaze.Dao.*;
import com.example.Amaze.Dto.RequestDto.CheckoutRequest;
import com.example.Amaze.Dto.RequestDto.ItemRequest;
import com.example.Amaze.Dto.ResponseDto.CartResponse;
import com.example.Amaze.Dto.ResponseDto.OrderResponse;
import com.example.Amaze.Enum.ProductStatus;
import com.example.Amaze.Exception.*;
import com.example.Amaze.Model.*;
import com.example.Amaze.Service.CartService;
import com.example.Amaze.Service.CustomerService;
import com.example.Amaze.Service.OrderService;
import com.example.Amaze.Transformer.CartTransformer;
import com.example.Amaze.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    CartRepo cartRepo;

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    OrderService orderService;

    @Autowired
    CardRepo cardRepo;
    @Override
    public Cart generateCart(Customer customer) {
        Cart cart = Cart.builder().cartTotal(0).customer(customer).build();
        return cart;
    }

    @Override
    public CartResponse addtoCart(Items item, ItemRequest itemRequest) {

        Customer customer = customerRepo.findByEmail(itemRequest.getEmail());
        Product product = productRepo.findById(itemRequest.getProduct_Id()).get();
        Cart cart = customer.getCart();

        cart.setCartTotal(cart.getCartTotal() + product.getPrice() * item.getRequiredQuantity());
        cart.getItems().add(item);
        item.setCart(cart);
        item.setProduct(product);

        Cart savedCart = cartRepo.save(cart); // save both cart and item
        Items savedItem = cart.getItems().get(cart.getItems().size()-1);

        product.getItems().add(savedItem);

        return CartTransformer.entityTodto(savedCart);
    }

    @Override
    public OrderResponse checkoutCart(CheckoutRequest checkoutRequest) throws CustomerNotFound, InvalidCardException, EmptyCartException, InsufficientQuantity {
        Customer customer = customerRepo.findByEmail(checkoutRequest.getEmail());
        if(customer == null) throw new CustomerNotFound("Customer Not Exist..");

        Cards card = cardRepo.findBycardNumber(checkoutRequest.getCardNumber());
        Date date = new Date();

        if(card == null || card.getCvv()!= checkoutRequest.getCvv() || date.after(card.getExpiry()))
            throw new InvalidCardException("Sorry! you can't use this card.");

        Cart cart = customer.getCart();
        if(cart.getItems().isEmpty()) throw new EmptyCartException("Cart is Empty!..");

        try {
            OrderEntity orderEntity = orderService.placeOrder(cart,card);
            resetCart(cart);
            OrderEntity savedOrder = orderRepo.save(orderEntity);
            customer.getOrderEntities().add(savedOrder);

            return OrderTransformer.entityTodto(savedOrder);


        } catch (InsufficientQuantity e) {
            throw e;
        }




    }
    public void resetCart(Cart cart){
        cart.setCartTotal(0);
        for(Items items : cart.getItems()){
            items.setCart(null);
        }
        cart.setItems(new ArrayList<>());
    }


}
