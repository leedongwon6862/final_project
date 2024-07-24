package com.example.final_project.category;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    //카테고리 폼
    @GetMapping("/add")
    public String addCategory(){
        return "category_add";
    }

    //카테고리 추가
    @PostMapping("/add")
    public String addCategory(String title){
        categoryService.addCategory(title);
        return "redirect:/product/list";
    }

    //카테고리 리스트
    @GetMapping("/list")
    public String getCategoryList(Model model){
        List<Category> categories = categoryService.getCategoryList();
        model.addAttribute("categories" ,categories);
        return "category_list";
    }

    //카테고리 수정 폼
    @GetMapping("/update/{id}")
    public String updateCategory(@PathVariable("id")Long id ,Model model){
        Category category = categoryService.getCategoryById(id).get();
        model.addAttribute("category" ,category);
       return  "category_update";
    }

    //카테고리 수정
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable("id")Long id ,String title){
        categoryService.updateCategory(id,title);
        return "redirect:/category/list";
    }

    //카테고리 삭제 폼
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id , Model model){
        Category category = categoryService.getCategoryById(id).get();
        model.addAttribute("category" , category);
        return "category_delete";

    }

    //카테고리 삭제
    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id")Long id){
        categoryService.deleteCategory(id);
        return "redirect:/category/list";
    }

    //카테고리 별 상품 상세 보기
    @GetMapping("/detail/{id}")
    public String detailCategory(@PathVariable("id")Long id , Model model){
        Category category = categoryService.getCategoryById(id).get();
    return"category_detail";
    }

}
