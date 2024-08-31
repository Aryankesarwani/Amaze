package com.example.Amaze.Transformer;

import com.example.Amaze.Dto.ResponseDto.ProductResponse;
import com.example.Amaze.Dto.ReuestDto.ProductRequest;
import com.example.Amaze.Enum.ProductStatus;
import com.example.Amaze.Model.Product;

public class ProductTransformer {

    public static Product dtoToentity(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.getProductName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .category(productRequest.getCategory())
                .build();
    }

    public static ProductResponse entityTodto(Product product) {
        return ProductResponse.builder()
                .productName(product.getName())
                .price(product.getPrice())
                .productStatus(ProductStatus.AVAILABLE)
                .sellerName(product.getSeller().getName())
                .build();
    }
}
