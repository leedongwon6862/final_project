package com.example.final_project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
     private final ProductRepository productRepository;
    public List<Product> getProducts() {
        return  productRepository.findAll();
    }

    public Product addProduct(String title, Integer price, String description) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        return productRepository.save(product);

    }

    public Optional<Product> showProduct(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(Long id) {
         productRepository.deleteById(id);
    }
}
