package com.example.final_project.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void addCategory(String title) {
        Category category = new Category();
        category.setTitle(title);
        categoryRepository.save(category);
    }

    public Optional<Category> getCategoryById(Long categoryId) {
       return  categoryRepository.findById(categoryId);
    }

    public Category updateCategory(Long id, String title) {
        Category newCategory = getCategoryById(id).get();
         newCategory.setTitle(title);
         return categoryRepository.save(newCategory);
    }

    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
