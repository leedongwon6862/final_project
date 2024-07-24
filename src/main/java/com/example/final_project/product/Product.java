package com.example.final_project.product;


import com.example.final_project.category.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer price;

    private String description;

    private String url;

    @ManyToOne
    private Category category;
}
