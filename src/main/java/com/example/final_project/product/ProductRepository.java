package com.example.final_project.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByTitleContaining(String title,Pageable pageable ) ;
    Page<Product> findByCategoryId(Long categoryId,Pageable pageable);
    Page<Product> findAll(Pageable pageable);
}
