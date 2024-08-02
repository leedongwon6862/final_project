package com.example.final_project.product;



import com.example.final_project.Cart.Cart;
import com.example.final_project.category.Category;
import com.example.final_project.comment.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //상품 아이디

    private String title; // 상품 이름

    private Integer price; // 상품 가격

    private String description; // 상품 설명

    private String url; // 상품 이미지

    @ManyToOne   //카테고리 연결
    private Category category;

    @OneToMany(mappedBy = "product" , cascade =  CascadeType.REMOVE)
    private List<Comment> commentList;

}
