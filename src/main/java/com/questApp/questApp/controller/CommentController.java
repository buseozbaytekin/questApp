package com.questApp.questApp.controller;

import com.questApp.questApp.entity.Comment;
import com.questApp.questApp.request.commentRequest.CommentCreateRequest;
import com.questApp.questApp.request.commentRequest.CommentUpdateRequest;
import com.questApp.questApp.response.CommentResponse;
import com.questApp.questApp.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @GetMapping
    public List<CommentResponse> getComments (@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return commentService.getComments(userId, postId);
    }

    @GetMapping("/{commentId}")
    public Comment getComment (@PathVariable Long commentId){
        return commentService.getComment(commentId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest request){
        return commentService.createComment(request);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest request){
        return commentService.updateComment(commentId, request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment (@PathVariable Long commentId){
         commentService.deleteComment(commentId);
    }
}
