package com.questApp.questApp.request;

import lombok.Data;

@Data
public class RefreshRequest {
    private Long userId;
    private String refreshToken;
}
