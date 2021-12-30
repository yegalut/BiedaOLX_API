package com.example.biedaolx_api.controller;


import com.example.biedaolx_api.dto.EditPostDto;
import com.example.biedaolx_api.dto.PostDto;
import com.example.biedaolx_api.dto.PostFilters;
import com.example.biedaolx_api.entity.Post;
import com.example.biedaolx_api.service.JwtService;
import com.example.biedaolx_api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("**")
public class PostController {

    private final PostService postService;
    private final JwtService jwtService;

    @GetMapping("/getPosts")
    public List<PostDto> getPosts(){
        return postService.findAllPosts();
    }

    @GetMapping("/getFilteredPosts")
    public List<PostDto> getFilteredPosts(@RequestParam(required = false, defaultValue = "")
                                                      String keyword ,
                                          @RequestParam(required = false, defaultValue = "")
                                                      String category,
                                          @RequestParam(required = false, defaultValue = "")
                                                      String maxPrice,
                                          @RequestParam(required = false, defaultValue = "")
                                                      String minPrice,
                                          @RequestParam(required = false, defaultValue = "")
                                                      String username,
                                          @RequestParam(required = false, defaultValue = "0")
                                                      String page){

        return postService.findAllFilteredPosts(keyword, category, maxPrice, minPrice, page, username);
    }


    @GetMapping("/getPost")
    public PostDto getPost(@RequestParam(name = "id") Long id){
        return postService.findPostById(id);
    }

    @PostMapping("/addPost")
    public PostDto addPost(@RequestBody Post post){
        return postService.addPost(post);
    }

    @PutMapping("/editPost")
    public PostDto editPost(@RequestBody EditPostDto post){
        return postService.editPost(post);
    }

    @DeleteMapping("/deletePost/{id}")
    public void deletePost(@PathVariable("id") Long id){
        postService.deletePost(id);
    }



}
