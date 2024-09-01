package com.example.Amaze.Controller;

import com.example.Amaze.Dto.ResponseDto.ProductResponse;
import com.example.Amaze.Dto.RequestDto.ProductRequest;
import com.example.Amaze.Enum.Category;
import com.example.Amaze.Exception.ProductAlreadyExistException;
import com.example.Amaze.Exception.SellerNotFound;
import com.example.Amaze.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@RequestBody ProductRequest productRequest) throws SellerNotFound, ProductAlreadyExistException {
        try {
            ProductResponse productResponse = productService.addProduct(productRequest);
            return new ResponseEntity(productResponse, HttpStatus.CREATED);
        }catch (SellerNotFound e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/get/category/{category}/price/{price}")
    public ResponseEntity getAllByCategoryandPrice(@PathVariable("category") Category category, @PathVariable("price") Integer price){

        List<ProductResponse> productResponses = productService.getAllByCategoryandPrice(category,price);

        return new ResponseEntity(productResponses,HttpStatus.FOUND);
    }

}
