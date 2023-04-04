package com.questApp.questApp.service;

import com.questApp.questApp.entity.Comment;
import com.questApp.questApp.entity.Post;
import com.questApp.questApp.entity.User;
import com.questApp.questApp.repository.CommentRepository;
import com.questApp.questApp.request.commentRequest.CommentCreateRequest;
import com.questApp.questApp.request.commentRequest.CommentUpdateRequest;
import com.questApp.questApp.response.CommentResponse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<CommentResponse> getComments(Optional<Long> userId, Optional<Long> postId) {
        List<Comment> comments;
        if(userId.isPresent() && postId.isPresent()){
            comments = commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if(userId.isPresent()){
            comments = commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            comments = commentRepository.findByPostId(postId.get());
        }else
            comments = commentRepository.findAll();
        return comments.stream().map(CommentResponse::new).collect(Collectors.toList());    }

    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createComment(CommentCreateRequest request) {
        User user = userService.getUserById(request.getUserId());
        Post post = postService.getPostById(request.getPostId());
        if (user != null && post != null){
            Comment comment = new Comment();
            comment.setId(request.getId());
            comment.setPost(post);
            comment.setUser(user);
            comment.setText(request.getText());
            comment.setCommentDate(new Date());
            return commentRepository.save(comment);
        }else
            return null;
    }

    public Comment updateComment(Long commentId, CommentUpdateRequest request) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()){
            Comment updatedComment = comment.get();
            updatedComment.setText(request.getText());
            return commentRepository.save(updatedComment);
        }else
            return null;
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
