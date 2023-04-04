package com.questApp.questApp.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthResponse {
    String message;
    Long userId;
    String accessToken;
    String refreshToken;
}
