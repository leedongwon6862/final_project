package com.example.final_project.comment;


import com.example.final_project.product.Product;
import com.example.final_project.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ProductService productService;

    public void createComment(String content, String username, Long parentId) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUsername(username);
        Product product = productService.showProduct(parentId).get();
        comment.setProduct(product);

        commentRepository.save(comment);


    }



}
