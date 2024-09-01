package com.example.Amaze.Service.Impl;

import com.example.Amaze.Dao.ProductRepo;
import com.example.Amaze.Dao.SellerRepo;
import com.example.Amaze.Dto.ResponseDto.ProductResponse;
import com.example.Amaze.Dto.RequestDto.ProductRequest;
import com.example.Amaze.Enum.Category;
import com.example.Amaze.Exception.ProductAlreadyExistException;
import com.example.Amaze.Exception.SellerNotFound;
import com.example.Amaze.Model.Product;
import com.example.Amaze.Model.Seller;
import com.example.Amaze.Service.ProductService;
import com.example.Amaze.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    SellerRepo sellerRepo;
    @Override
    public ProductResponse addProduct(ProductRequest productRequest) throws SellerNotFound, ProductAlreadyExistException {

        Seller seller = sellerRepo.findByEmail(productRequest.getSellerEmail());

        if(seller == null) throw new SellerNotFound("Email-Id is not Registered!!...");



        //dto to entity
        Product product = ProductTransformer.dtoToentity(productRequest);

        int index = seller.getProduct().indexOf(product);
        if(index!=-1){
            throw new ProductAlreadyExistException("Product is Already Exist you need to update it or delete it");
        }


        product.setSeller(seller);
        seller.getProduct().add(product);
        //save product
        Seller savedSeller = sellerRepo.save(seller); //save both seller and product
        Product savedProduct = savedSeller.getProduct().get(savedSeller.getProduct().size()-1);

        return ProductTransformer.entityTodto(savedProduct);
    }

    @Override
    public List<ProductResponse> getAllByCategoryandPrice(Category category, Integer price) {

        List<Product> products = productRepo.findByCategoryAndPrice(category,price);
        //prepare a list of Dtos

        List<ProductResponse> productResponses = new ArrayList<>();
        for(Product product : products){
//            System.out.println(ProductTransformer.entityTodto(product));
            productResponses.add(ProductTransformer.entityTodto(product));
//            System.out.println(productResponses.get(productResponses.size()-1));
        }

        return productResponses;

    }
}
