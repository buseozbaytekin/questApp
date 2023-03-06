package com.questApp.questApp.controller;

import com.questApp.questApp.entity.Post;
import com.questApp.questApp.request.postRequest.PostCreateRequest;
import com.questApp.questApp.request.postRequest.PostUpdateRequest;
import com.questApp.questApp.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getPosts (@RequestParam Optional<Long> userId){
        return postService.getPosts(userId);
    }

    @GetMapping("/{postId}")
    public Post getPost (@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public Post createPost (@RequestBody PostCreateRequest newPostRequest){
        return postService.createPost(newPostRequest);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest){
        return postService.updatePost(postId, postUpdateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }

}
