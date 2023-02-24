package com.questApp.questApp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    private Long id;
    private Long postId;
    private Long userId;

    @Lob
    @Column(columnDefinition = "text")
    String text;

}
