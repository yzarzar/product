package com.example.springapi.controller;

import com.example.springapi.entity.Product;
import com.example.springapi.response.Products;
import com.example.springapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product).getBody();
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping(value = "/find" ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Products listAllProducts() {
        return productService.listAllProducts();
    }

    @GetMapping(value = "/find/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> findProductById(@PathVariable int id) {
        Product productResponse = productService.findProductById(id);
        if (productResponse != null) {
            return ResponseEntity.ok(productResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.ok("Product deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{productId}")
    public Product updateProduct(@PathVariable int productId,@RequestBody Product updateProduct) {
        return productService.updateProduct(productId,updateProduct);
    }

    @GetMapping(value = "/find/search",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> searchProductsByTitle(@RequestParam String keyword) {
        return productService.searchByTitleContaining(keyword);
    }

}