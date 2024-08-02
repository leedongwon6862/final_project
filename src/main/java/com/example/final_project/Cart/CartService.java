package com.example.final_project.Cart;

import com.example.final_project.product.Product;
import com.example.final_project.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;


    public void save(Cart cart){
        cartRepository.save(cart);
    }

    public void add(Product product, SiteUser user,Cart cart){
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setCount(1);
        cartItemRepository.save(cartItem);
    }

    public CartItem findById(Long id) {
        return cartItemRepository.findById(id).orElseThrow();
    }

    public void saveItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }
}
