package com.example.biedaolx_api.service;

import com.example.biedaolx_api.entity.Post;
import com.example.biedaolx_api.entity.PostCategory;
import com.example.biedaolx_api.repository.PostCategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCategoryService {

    private final PostCategoryRepo postCategoryRepo;

    //get post categories
    public List<String> getPostCategories(){
        return postCategoryRepo.getAllCategoryNames();
    }

    //adding post category
    public PostCategory addPostCategory(PostCategory postCategory) {

        if (postCategoryRepo.findByName(postCategory.getName())!=null) {
            throw new IllegalArgumentException("This post category already exists");
        } else {
            return postCategoryRepo.save(postCategory);
        }
    }

    //delete post category
    public void deletePostCategory(Long id){
        postCategoryRepo.delete(postCategoryRepo.findById(id).orElseThrow());
    }

    //edit post category
    public PostCategory editPostCategory(PostCategory postCategory){
        if(postCategoryRepo.findById(postCategory.getId()).isPresent()){
            return postCategoryRepo.save(postCategory);
        }else{
            throw new IllegalArgumentException("Category with this id does not exist");
        }
    }


}
