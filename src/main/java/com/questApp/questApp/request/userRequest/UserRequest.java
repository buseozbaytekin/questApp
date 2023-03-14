package com.questApp.questApp.request.userRequest;

import lombok.Data;

@Data
public class UserRequest {
    private Long id;
    private String userName;
    private String password;

}