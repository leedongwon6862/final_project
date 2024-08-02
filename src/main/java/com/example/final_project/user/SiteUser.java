package com.example.final_project.user;



import com.example.final_project.Cart.Cart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class SiteUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
