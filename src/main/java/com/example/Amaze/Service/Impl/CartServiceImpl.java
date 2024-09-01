package com.example.Amaze.Service.Impl;

import com.example.Amaze.Dao.CartRepo;
import com.example.Amaze.Dao.CustomerRepo;
import com.example.Amaze.Dao.ProductRepo;
import com.example.Amaze.Dto.RequestDto.ItemRequest;
import com.example.Amaze.Dto.ResponseDto.CartResponse;
import com.example.Amaze.Exception.ProductNotFoundException;
import com.example.Amaze.Model.Cart;
import com.example.Amaze.Model.Customer;
import com.example.Amaze.Model.Items;
import com.example.Amaze.Model.Product;
import com.example.Amaze.Service.CartService;
import com.example.Amaze.Service.CustomerService;
import com.example.Amaze.Transformer.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductRepo productRepo;

    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    CartRepo cartRepo;
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
}
