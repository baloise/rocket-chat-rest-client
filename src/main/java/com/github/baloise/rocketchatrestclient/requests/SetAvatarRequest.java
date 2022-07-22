package com.github.baloise.rocketchatrestclient.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SetAvatarRequest {

    private byte[] image;
    private String userId;

    public SetAvatarRequest(String userId, byte[] image) {
        this.userId = userId;
        this.image = image;
    }
}
