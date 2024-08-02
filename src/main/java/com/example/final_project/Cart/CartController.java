package com.example.final_project.Cart;

import com.example.final_project.product.Product;
import com.example.final_project.product.ProductService;
import com.example.final_project.user.SiteUser;
import com.example.final_project.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id,  Principal principal, Model model){
        Product product = productService.showProduct(id).get();
        SiteUser user = userService.findByName(principal.getName());
        Cart cart = user.getCart();
        cartService.add(product,user,cart);

        List<CartItem> cartItemList = user.getCart().getCartItemList();
        model.addAttribute("cartList",cartItemList);

        return "redirect:/cart/";

    }

    @GetMapping("/")
    public String list(Principal principal, Model model){
        SiteUser user = userService.findByName(principal.getName());
        List<CartItem> cartItemList = user.getCart().getCartItemList();
        model.addAttribute("cartList",cartItemList);

        return "cart";
    }

    @PostMapping("/update/{id}")
    public String countPlus(@PathVariable("id") Long id){
      CartItem cartItem = cartService.findById(id);
      cartItem.setCount(cartItem.getCount()+1);
      cartService.saveItem(cartItem);

      return "redirect:/cart/";
    }
}
