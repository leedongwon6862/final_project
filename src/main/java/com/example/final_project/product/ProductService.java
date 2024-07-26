package com.example.final_project.product;

import com.example.final_project.category.Category;
import com.example.final_project.category.CategoryRepository;
import com.example.final_project.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ResourceLoader resourceLoader;
     private final ProductRepository productRepository;
     private final CategoryService categoryService;



    public Page<Product> getProducts(int page) {
        Pageable pageable = PageRequest.of(page ,12);
        return  productRepository.findAll(pageable);
    }

    public Product addProduct(String title, Integer price, String description, MultipartFile file ,Long categoryId) {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        product.setCategory(category.get());


        if (!file.isEmpty())  //내가 파일 업로드 한 경우
            try {
                String path = "C:/web"; // 저장할 기본 경로 (c 드라 이브) , 문자열 로 저장
                File fileFolder = new File(path + "/image"); // c:/web/image (최종 경로) ,문자열 기반 실제 파일 시스템 의   파일 나타 내는 객체 생성

                if (!fileFolder.exists())
                    fileFolder.mkdirs();
                String filePath = "/image/" + UUID.randomUUID().toString() + "." + file.getContentType().split("/")[1];
                //"/image/" 디렉 토리 아래에 고유한 UUID 를 사용  파일 이름을 생성, 파일의 확장자 를 포함한 경로를 문자열 로 만듬
                file.transferTo(Paths.get(path + filePath));
                //실제 파일 저장
                product.setUrl(filePath);
                //저장된 파일 경로(데이터 베이스 에 저장할 정보)를 Product 객체에 설정
            } catch (IOException ignored) {
                ignored.printStackTrace();
            } // 파일 저장 중 오류가 발생 -> 오류 무시 -> 스택 트레 이스 를 출력 문제 확인



        return productRepository.save(product);

    }

    public Optional<Product> showProduct(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(Long id) {
         productRepository.deleteById(id);
    }

    public void updateProduct(Long id, String description, String title, Integer price, String url) {
        Product newProduct = showProduct(id).get();
        newProduct.setDescription(description);
        newProduct.setTitle(title);
        newProduct.setUrl(url);
        newProduct.setTitle(title);
        newProduct.setPrice(price);
        productRepository.save(newProduct);
    }



    public Page<Product> findByCategoryId(Long categoryId,int page) {
        Pageable pageable = PageRequest.of(page,12);
        return productRepository.findByCategoryId(categoryId,pageable);
    }


    public Page<Product> findByTitleContaining(String searchTitle,int page) {
    Pageable pageable = PageRequest.of(page,12);
        return productRepository.findByTitleContaining(searchTitle,pageable);
    }
}
