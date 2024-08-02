package com.example.final_project.Cart;

import com.example.final_project.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int count;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    private Cart cart;
}
