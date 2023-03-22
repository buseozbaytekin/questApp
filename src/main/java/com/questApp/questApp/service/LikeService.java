package com.questApp.questApp.service;

import com.questApp.questApp.entity.Like;
import com.questApp.questApp.entity.Post;
import com.questApp.questApp.entity.User;
import com.questApp.questApp.repository.LikeRepository;
import com.questApp.questApp.request.likeRequest.LikeCreateRequest;
import com.questApp.questApp.response.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {
    LikeRepository likeRepository;
    UserService userService;
    PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<LikeResponse> getLikes(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if(userId.isPresent() && postId.isPresent()){
            list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if(userId.isPresent()){
            list = likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            list = likeRepository.findByPostId(postId.get());
        }else
            list = likeRepository.findAll();
        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }

    public Like getLike(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createLike(LikeCreateRequest request) {
        User user = userService.getUserById(request.getUserId());
        Post post = postService.getPostById(request.getPostId());
        if(post != null && user != null){
            Like like = new Like();
            like.setId(request.getId());
            like.setPost(post);
            like.setUser(user);
            return likeRepository.save(like);
        }else
            return null;
    }

    public void deleteLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
