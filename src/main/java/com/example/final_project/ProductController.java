package com.example.final_project;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    //상품 리스트
    @GetMapping("/list")
    public String getProducts(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "product_list";
    }


    //상품 추가 폼
    @GetMapping("/add")
    public String addProduct() {
        return "product_add";
    }

    //상품 추가 기능
    @PostMapping("/add")
    public String addProduct(String title, Integer price, String description, MultipartFile file) {
        productService.addProduct(title, price, description,file);
        return "redirect:/product/list";
    }

    //상품 상세 보기
    @GetMapping("/detail/{id}")
    public String showProduct(@PathVariable("id") Long id, Model model) {
        Optional<Product> showProduct = productService.showProduct(id);
        if (showProduct.isPresent()) {
            model.addAttribute("showProduct", showProduct.get());
            // Optional 처리 했으면 .get 으로 가져 오기
            return "product_detail";
        }else return "redirect:/product/list";
    }

    //상품 삭제 폼
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id,Model model) {
        Optional<Product> product = productService.showProduct(id);
        if(product.isPresent()){
            model.addAttribute("product",product.get());
            return "product_delete";
        }
        else return "redirect:/product/list";
    }


    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id")Long id){
        productService.deleteProduct(id);
            return "redirect:/product/list";

    }


}
