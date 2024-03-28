package com.lolpee.server.domain.user.dto;

import lombok.Builder;
import lombok.Data;

public class AuthData {
    @Builder
    @Data
    public static class SignUp {
        private String username;
        private String password;
        private String providerId;
        private String email;


    }
}
