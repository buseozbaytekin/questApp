package com.questApp.questApp.response;

import com.questApp.questApp.entity.Like;
import lombok.Data;

@Data
public class LikeResponse {
    private Long id;
    private Long userId;
    private Long postId;

    public LikeResponse(Like entity){
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.postId = entity.getPost().getId();
    }
}
