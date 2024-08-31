package com.example.Amaze.Service;

import com.example.Amaze.Dto.ResponseDto.ProductResponse;
import com.example.Amaze.Dto.ReuestDto.ProductRequest;
import com.example.Amaze.Enum.Category;
import com.example.Amaze.Exception.ProductAlreadyExistException;
import com.example.Amaze.Exception.SellerNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest) throws SellerNotFound, ProductAlreadyExistException;

    List<ProductResponse> getAllByCategoryandPrice(Category categery, Integer price);
}
