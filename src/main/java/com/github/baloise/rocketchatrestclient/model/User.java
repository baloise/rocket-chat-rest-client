package com.github.baloise.rocketchatrestclient.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonGetter;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User extends Identified {
    private String username, name;
    private String[] roles;
    private Email[] emails;
    private Date createdAt, lastLogin;
    private UserType type;
    private UserStatus status, statusConnection;
    private Map<String, String> customFields;
    private boolean active;
    private int utcOffset;
    private String avatarETag;

    /**
     * Sets the user's {@link UserStatus Connection Status}, which is whether they're connected to
     * the server or not.
     *
     * @param status the connection status of the user
     */
    public void setStatusConnection(UserStatus status) {
        //TODO: This check might not be needed, however "BUSY" isn't a valid connection status unless something changes
        if (status != UserStatus.ONLINE && status != UserStatus.OFFLINE && status != UserStatus.AWAY)
            throw new IllegalArgumentException("The provided status is not a valid one for Connection Status.");

        this.statusConnection = status;
    }
}
