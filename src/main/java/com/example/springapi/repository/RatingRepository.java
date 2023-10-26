package com.example.springapi.repository;

import com.example.springapi.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating,Integer> {
}
