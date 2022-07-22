package com.github.baloise.rocketchatrestclient.requests;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
public class CreateUserRequest {

    private String email, name, password, username;
    private Boolean active, verified;
    private String[] roles;
    private Map<String, String> customFields;

    public CreateUserRequest(String email, String name, String password, String username, boolean verified, String[] roles) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.username = username;
        this.verified = verified;
        this.roles = roles;
    }



}
