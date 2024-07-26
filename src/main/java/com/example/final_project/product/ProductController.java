package com.example.final_project.product;


import com.example.final_project.category.Category;
import com.example.final_project.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    //상품 리스트
    @GetMapping("/list")
    public String getProducts(Model model, @RequestParam(defaultValue = "0") Long categoryId,
                              @RequestParam(defaultValue = "") String searchTitle,
                              @RequestParam(value="page",defaultValue = "0") int page) {


        // 카테고리 ID가 0일 경우 모든 상품을 가져옴
        if (categoryId == 0 && searchTitle.length() == 0) {
            Page<Product> products = productService.getProducts(page);
            model.addAttribute("products", products);
        } else if (categoryId != 0) {
            // 카테고리 ID가 0이 아닐 경우 해당 카테고리에 속하는 상품을 가져옴
            Page<Product> products = productService.findByCategoryId(categoryId,page);
            model.addAttribute("products", products);
        } else if (categoryId == 0 && searchTitle.length() != 0) {
            Page<Product> products = productService.findByTitleContaining(searchTitle,page);
            model.addAttribute("products", products);
        }

        // 카테고리 목록을 항상 가져옴
        List<Category> categories = categoryService.getCategoryList();

        // 모델에 데이터 추가

        model.addAttribute("categories", categories);

        return "product_list";
    }


    //상품 추가 폼
    @GetMapping("/add")
    public String addProduct(Model model) {
        List<Category> categories = categoryService.getCategoryList();
        model.addAttribute("categories", categories);

        return "product_add";
    }

    //상품 추가 기능
    @PostMapping("/add")
    public String addProduct(String title, Integer price, String description, MultipartFile file, Long categoryId) {
        productService.addProduct(title, price, description, file, categoryId);
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
        } else return "redirect:/product/list";
    }

    //상품 삭제 폼
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productService.showProduct(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product_delete";
        } else return "redirect:/product/list";
    }


    //상품 삭제 기능
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/product/list";

    }

    //상품 수정 폼

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService.showProduct(id).get();
        model.addAttribute("product", product);
        return "product_update";
    }


    //상품 수정
    @PostMapping("/update/{id}")
    public String updateProduct(Model model, @PathVariable("id") Long id, String description, String title, Integer price, String url) {
        productService.updateProduct(id, description, title, price, url);
        return "redirect:/product/list";
    }


}




