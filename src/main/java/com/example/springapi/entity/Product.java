package com.example.springapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private double price;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String category;
    private String image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rating")
    private Rating rating;
}
