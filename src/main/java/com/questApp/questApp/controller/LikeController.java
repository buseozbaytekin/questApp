package com.questApp.questApp.controller;

import com.questApp.questApp.entity.Like;
import com.questApp.questApp.request.commentRequest.CommentCreateRequest;
import com.questApp.questApp.request.likeRequest.LikeCreateRequest;
import com.questApp.questApp.service.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/like")
public class LikeController {
    LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }
    @GetMapping
    public List<Like> getLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return likeService.getLikes(userId, postId);
    }
    @GetMapping("/{likeId}")
    public Like getLike(@PathVariable Long likeId){
        return likeService.getLike(likeId);
    }
    @PostMapping
    public Like createLike(@RequestBody LikeCreateRequest request){
        return likeService.createLike(request);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLike(@PathVariable Long likeId){
         likeService.deleteLike(likeId);
    }

}
