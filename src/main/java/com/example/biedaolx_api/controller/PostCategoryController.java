package com.example.biedaolx_api.controller;

import com.example.biedaolx_api.entity.Post;
import com.example.biedaolx_api.entity.PostCategory;
import com.example.biedaolx_api.service.PostCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("**")
public class PostCategoryController {
    private final PostCategoryService postCategoryService;


    @GetMapping("/getPostCategories")
    public List<String> getPostCategories(){
        return postCategoryService.getPostCategories();
    }

    @PostMapping("/addPostCategory")
    public PostCategory addPostCategory(@RequestBody PostCategory postCategory){
        return postCategoryService.addPostCategory(postCategory);
    }

    @PutMapping("/editPostCategory")
    public PostCategory editPostCategory(@RequestBody PostCategory postCategory){
        return postCategoryService.editPostCategory(postCategory);
    }

    @DeleteMapping("/deletePostCategory/{id}")
    public void deletePostCategory(@PathVariable("id") Long id){
        postCategoryService.deletePostCategory(id);

    }
}
