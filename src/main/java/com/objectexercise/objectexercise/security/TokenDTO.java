package com.objectexercise.objectexercise.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class TokenDTO {

    private String access_token;

    private String refresh_token;
}
