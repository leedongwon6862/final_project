package com.example.final_project.user;

import com.example.final_project.Cart.Cart;
import com.example.final_project.Cart.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;

    @Transactional
    public SiteUser UserCreate(String username , String password , String email){
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);
        siteUser.setPassword(passwordEncoder.encode(password));
        siteUser.setEmail(email);
        Cart cart = new Cart();
        siteUser.setCart(cart);
        cartService.save(cart);
         return userRepository.save(siteUser);
    }

    public SiteUser findById(Long userId) {
      return  userRepository.findById(userId).get();
    }

    public SiteUser findByName(String name) {
        return userRepository.findByUsername(name).orElseThrow(()-> new RuntimeException("로그인 해주세요"));
    }
}
