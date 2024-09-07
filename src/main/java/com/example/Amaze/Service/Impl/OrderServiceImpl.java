package com.example.Amaze.Service.Impl;

import com.example.Amaze.Dao.CardRepo;
import com.example.Amaze.Dao.CustomerRepo;
import com.example.Amaze.Dao.OrderRepo;
import com.example.Amaze.Dao.ProductRepo;
import com.example.Amaze.Dto.RequestDto.OrderRequest;
import com.example.Amaze.Dto.ResponseDto.OrderResponse;
import com.example.Amaze.Enum.ProductStatus;
import com.example.Amaze.Exception.CustomerNotFound;
import com.example.Amaze.Exception.InsufficientQuantity;
import com.example.Amaze.Exception.InvalidCardException;
import com.example.Amaze.Exception.ProductNotFoundException;
import com.example.Amaze.Model.*;
import com.example.Amaze.Service.OrderService;
import com.example.Amaze.Transformer.ItemTransformer;
import com.example.Amaze.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    ProductRepo productRepo;

    @Autowired
    CardRepo cardRepo;
    @Autowired
    OrderRepo orderRepo;



    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) throws CustomerNotFound, ProductNotFoundException, InsufficientQuantity, InvalidCardException {
        Customer customer = customerRepo.findByEmail(orderRequest.getEmail());
        if(customer == null) throw new CustomerNotFound("Customer Doesn't Exist...");

        Optional<Product> optionalProduct = productRepo.findById(orderRequest.getProductId());
        if(optionalProduct.isEmpty()) throw new ProductNotFoundException("Product Doesn't Exist...");

        Product product = optionalProduct.get();

        if(product.getQuantity() == 0) {
            throw new InsufficientQuantity("Product Out of Stock...");
        }

        if(product.getQuantity() < orderRequest.getRequiredQuantity()) {
            throw new InsufficientQuantity("Sorry, The required quantity is not available");
        }

        // card

        Cards card = cardRepo.findBycardNumber(orderRequest.getCardNo());
        Date date = new Date();

        if(card == null || card.getCvv()!= orderRequest.getCvv() || date.after(card.getExpiry()))
            throw new InvalidCardException("Sorry! you can't use this card.");

        int newQuantity = product.getQuantity() - orderRequest.getRequiredQuantity();
        product.setQuantity(newQuantity);
        if(newQuantity == 0) product.setProductStatus(ProductStatus.OUT_OF_STOCK);

        // create Item
        Items item = ItemTransformer.entityToitem(orderRequest.getRequiredQuantity());
        item.setProduct(product);


        // create order
        OrderEntity orderEntity = OrderTransformer.dtoTOentity(item,customer);

        String maskedCard = generateMaskCardNo(card);
        orderEntity.setCardUsed(maskedCard);
        orderEntity.getItems().add(item);
        item.setOrderEntity(orderEntity);

        OrderEntity savedOrder = orderRepo.save(orderEntity);
        customer.getOrderEntities().add(savedOrder);

        product.getItems().add(savedOrder.getItems().get(0));


        // prepaer response dto

        OrderResponse orderResponse = OrderTransformer.entityTodto(savedOrder);

        return orderResponse;
    }

    @Override
    public OrderEntity placeOrder(Cart cart, Cards card) throws InsufficientQuantity {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo(UUID.randomUUID().toString());
        orderEntity.setCardUsed(generateMaskCardNo(card));
        int totalValue = 0;
        for(Items item : cart.getItems()){
            Product product = item.getProduct();
            if(product.getQuantity()<item.getRequiredQuantity()) {

                throw new InsufficientQuantity("Sorry!.. The required quantity is not available");
            }
            product.setQuantity(product.getQuantity() - item.getRequiredQuantity());
            if(product.getQuantity() == 0) product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            totalValue += product.getPrice() * item.getRequiredQuantity();
            item.setOrderEntity(orderEntity);
        }
        orderEntity.setTotalValue(totalValue);
        orderEntity.setItems(cart.getItems());
        orderEntity.setCustomer(cart.getCustomer());
        return orderEntity;
    }

    private String generateMaskCardNo(Cards cards){
        String CardNumber = "";
        String OrignalcardNumber = cards.getCardNumber();
        for(int i=0;i<cards.getCardNumber().length() - 4 ; i++){
            CardNumber += "x";
        }
        CardNumber = CardNumber + OrignalcardNumber.substring(OrignalcardNumber.length()-4);
        return CardNumber;
    }
}
