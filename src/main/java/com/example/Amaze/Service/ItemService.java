package com.example.Amaze.Service;

import com.example.Amaze.Dto.RequestDto.ItemRequest;
import com.example.Amaze.Exception.CustomerNotFound;
import com.example.Amaze.Exception.InsufficientQuantity;
import com.example.Amaze.Exception.ProductNotFoundException;
import com.example.Amaze.Model.Items;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {
    Items createItem(ItemRequest itemRequest) throws ProductNotFoundException, CustomerNotFound, InsufficientQuantity;
}
