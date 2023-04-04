package com.questApp.questApp.response;

import com.questApp.questApp.entity.Comment;
import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private Long userId;
    private String userName;
    String text;

    public CommentResponse(Comment entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.userName = entity.getUser().getUserName();
        this.text = entity.getText();
    }
}
