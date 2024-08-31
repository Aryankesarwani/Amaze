package com.example.Amaze.Model;

import com.example.Amaze.Enum.Category;
import com.example.Amaze.Enum.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name= "product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    Integer quantity;

    Integer price;

    @Enumerated(EnumType.STRING)
    Category category;

    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Items> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Seller seller;


}
