package com.example.biedaolx_api.service;

import com.example.biedaolx_api.dto.EditPostDto;
import com.example.biedaolx_api.dto.Mappers;
import com.example.biedaolx_api.dto.PostDto;
import com.example.biedaolx_api.dto.PostFilters;
import com.example.biedaolx_api.entity.Post;
import com.example.biedaolx_api.entity.QPost;
import com.example.biedaolx_api.repository.PostCategoryRepo;
import com.example.biedaolx_api.repository.PostRepo;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;
    private final PostCategoryRepo postCategoryRepo;



    //Adding a post
    public PostDto addPost(Post post){
        if(postRepo.findById(post.getId()).isPresent()){
            throw new IllegalArgumentException("This post already exists");
        }else {
            return Mappers.mapToPostDto(postRepo.save(post));
        }
    }

    //Deleting a post
    public void deletePost(Long id){
        postRepo.delete(postRepo.findById(id).orElseThrow());
    }

    //Editing a post
    public PostDto editPost(EditPostDto tepmPost){
        if(postRepo.findById(tepmPost.getId()).isPresent()){
            Post post = postRepo.getById(tepmPost.getId());

            post.setPrice(tepmPost.getPrice());
            post.setTitle(tepmPost.getTitle());
            post.setBody(tepmPost.getBody());
            post.setPostCategory(postCategoryRepo.findByName(tepmPost.getPostCategory()));




            return Mappers.mapToPostDto(postRepo.save(post));
        }else {
            throw new IllegalArgumentException("This post does not exist");
        }
    }

    //Get posts
    public List<PostDto> findAllPosts() {
        return postRepo.findAll()
                .stream()
                .map(Mappers::mapToPostDto)
                .collect(Collectors.toList());
    }

    //Get posts
    public List<PostDto> findAllFilteredPosts(String keyword,
                                              String category,
                                              String maxPrice,
                                              String minPrice,
                                              String page,
                                              String username) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        //check user
        if(!username.isEmpty()){
            booleanBuilder.and(QPost.post.user.username.eq(username));
        }

        //check max price
        if(!maxPrice.isEmpty()){
            booleanBuilder.and(QPost.post.price.loe(Integer.parseInt(maxPrice)));
        }

        //check min price in
        if(!minPrice.isEmpty()){
            booleanBuilder.and(QPost.post.price.goe(Integer.parseInt(minPrice)));
        }

        if(!category.isEmpty()){
            booleanBuilder.and(QPost.post.postCategory.eq(postCategoryRepo.findByName(category)));
        }

        // check if keyword in title or body
        if(!keyword.isEmpty()) {
            booleanBuilder.and(QPost.post.title.contains(keyword).or(QPost.post.body.contains(keyword)));
        }

        List<PostDto> postDtos = new ArrayList<>();

        if(booleanBuilder.getValue()!=null) {
            postRepo.findAll(booleanBuilder.getValue(), PageRequest.of(Integer.parseInt(page), 10, Sort.by("dateOfCreation").descending()))
                    .forEach(post -> postDtos.add(Mappers.mapToPostDto(post)));

            return postDtos;
        }
        return postRepo.findAll(PageRequest.of(Integer.parseInt(page),10, Sort.by("dateOfCreation").descending())).stream().map(Mappers::mapToPostDto).collect(Collectors.toList());
    }


    public PostDto findPostById(Long id) {
        return Mappers.mapToPostDto(postRepo.findById(id).orElseThrow());
    }

    // get filtered posts
    public List<PostDto> findFilteredPosts(PostFilters postFilters) {


        return null;

    }
}
