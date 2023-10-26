package com.example.springapi.response;

import com.example.springapi.entity.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@RequiredArgsConstructor
public class Products {

    private List<Product> products = new ArrayList<>();

    public Products(Spliterator<Product> productSpliterator) {
        products = StreamSupport.stream(productSpliterator,false)
                .collect(Collectors.toList());
    }
}
