
package com.example.springapi.service;

import com.example.springapi.entity.Product;
import com.example.springapi.repository.ProductRepository;
import com.example.springapi.entity.Rating;
import com.example.springapi.repository.RatingRepository;
import com.example.springapi.response.Products;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final RatingRepository ratingRepository;

    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Rating savedRating = ratingRepository.save(product.getRating());
        product.setRating(savedRating);
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    public Products listAllProducts() {
        return new Products(productRepository.findAll().spliterator());
    }

    public Product findProductById(int id) {
        return productRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        id + "not found!"));
    }

    public boolean deleteProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Product updateProduct(int productId,Product updatedProduct) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found."));
        existingProduct.setTitle(updatedProduct.getTitle());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setImage(updatedProduct.getImage());
        existingProduct.setRating(updatedProduct.getRating());
        return productRepository.save(existingProduct);
    }

    public List<Product> searchByTitleContaining(String keyword) {
        return productRepository.findByTitleContaining(keyword);
    }
}

