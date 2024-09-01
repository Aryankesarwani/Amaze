package com.example.Amaze.Service.Impl;

import com.example.Amaze.Dao.CustomerRepo;
import com.example.Amaze.Dao.ProductRepo;
import com.example.Amaze.Dto.RequestDto.ItemRequest;
import com.example.Amaze.Exception.CustomerNotFound;
import com.example.Amaze.Exception.InsufficientQuantity;
import com.example.Amaze.Exception.ProductNotFoundException;
import com.example.Amaze.Model.Customer;
import com.example.Amaze.Model.Items;
import com.example.Amaze.Model.Product;
import com.example.Amaze.Service.ItemService;
import com.example.Amaze.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    CustomerRepo customerRepo;
    @Override
    public Items createItem(ItemRequest itemRequest) throws ProductNotFoundException, CustomerNotFound, InsufficientQuantity {

        Customer customer = customerRepo.findByEmail(itemRequest.getEmail());
        if(customer == null) throw new CustomerNotFound("Customer Not Exist...!!");

        Optional<Product> product = productRepo.findById(itemRequest.getProduct_Id());
        if(product.isEmpty()) throw new ProductNotFoundException("Item not Exist!..");

        if(product.get().getQuantity() == 0) {
            throw new InsufficientQuantity("Product Out of Stock...");
        }

        if(product.get().getQuantity() < itemRequest.getQuantity()) {
            throw new InsufficientQuantity("Sorry, The required quantity is not available");
        }

        return ItemTransformer.entityToitem(itemRequest.getQuantity());
    }
}
