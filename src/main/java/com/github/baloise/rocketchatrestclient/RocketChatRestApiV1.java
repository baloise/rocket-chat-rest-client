package com.github.baloise.rocketchatrestclient;

import com.github.baloise.rocketchatrestclient.util.HttpMethods;

/**
 * Enumeration of the available REST API methods.
 *
 * @author Bradley Hilton (graywolf336)
 * @since 0.1.0
 * @version 0.0.1
 */
public enum RocketChatRestApiV1 {
    /** Adds <strong>all</strong> users to the specified channel. */
    ChannelsAddAll("channels.addAll", HttpMethods.POST, true),
    ChannelsAddModerator("channels.addModerator", HttpMethods.POST, true),
    ChannelsAddOwner("channels.addOwner", HttpMethods.POST, true),
    /** Archives a channel. */
    ChannelsArchive("channels.archive", HttpMethods.POST, true),
    /** Cleans up a channel (removing messages) */
    ChannelsCleanHistory("channels.cleanHistory", HttpMethods.POST, true),
    /** Closes a channel. */
    ChannelsClose("channels.close", HttpMethods.POST, true),
    /** Creates a new <strong>public</strong> channel. */
    ChannelsCreate("channels.create", HttpMethods.POST, true),
    /** Deletes the <strong>public</strong> channel from the server. */
    ChannelsDelete("channels.delete", HttpMethods.POST, true),
    ChannelsGetIntegrations("channels.getIntegrations", HttpMethods.GET, true),
    /** Retrieves a <strong>public</strong> channel's information. */
    ChannelsInfo("channels.info", HttpMethods.GET, true),
    /** Retrieves a list of all the <strong>public</strong> channels. */
    ChannelsList("channels.list", HttpMethods.GET, true),
    /** Unarchives a channel. */
    ChannelsUnarchive("channels.unarchive", HttpMethods.POST, true),
    /** Invites a user to join a channel **/
    ChannelsInvite("channels.invite", HttpMethods.POST, true),
    /** Removes a user from the channel **/
    ChannelsKick("channels.kick", HttpMethods.POST, true),
    /** Removes the callee from the channel **/
    ChannelsLeave("channels.leave", HttpMethods.POST, true),
    /** Renames the channel **/
    ChannelsRename("channels.rename", HttpMethods.POST, true),
    /** Adds the channel back to the user’s list of channels.**/
    ChannelsOpen("channels.open", HttpMethods.POST, true),
    /** Deletes a chat message. */
    ChatDelete("chat.delete", HttpMethods.POST, true),
    /** Sends a new chat message */
    ChatPostMessage("chat.postMessage", HttpMethods.POST, true),
    /* Adds an owner to the group*/
    GroupsAddOwner("groups.addOwner", HttpMethods.POST, true),
    /** Retrieves information about a <strong>private</strong> group, but only if the user is part of it. */
    GroupsInfo("groups.info", HttpMethods.GET, true),
    /** Retrieves a list of all the <strong>private</strong> groups the auth'd user has joined. */
    GroupsList("groups.list", HttpMethods.GET, true),
    /** Creates a new <strong>private</strong> group. */
    GroupsCreate("groups.create", HttpMethods.POST, true),
    /** Archives a group. */
    GroupsArchive("groups.archive", HttpMethods.POST, true),
    /** Unarchives a group. */
    GroupsUnarchive("groups.unarchive", HttpMethods.POST, true),
    /** Closes a group. */
    GroupsClose("groups.close", HttpMethods.POST, true),
    /** Invites a user to join a group **/
    GroupsInvite("groups.invite", HttpMethods.POST, true),
    /** Removes a user from the group **/
    GroupsKick("groups.kick", HttpMethods.POST, true),
    /** Removes the callee from the group **/
    GroupsLeave("groups.leave", HttpMethods.POST, true),
    /** Adds the group back to the user’s list of groups.**/
    GroupsOpen("groups.open", HttpMethods.POST, true),
    /** Renames the group **/
    GroupsRename("groups.rename", HttpMethods.POST, true),
    /** Retrieves a list of all the direct message rooms the auth'd user has. */
    ImsList("ims.list", HttpMethods.GET, true),
    /** Gets the information about the server, including version and build commit. */
    Info("info", HttpMethods.GET, false),
    /** Gets all settings on the server */
    SettingsGetAll("settings", HttpMethods.GET, true),
    /** Get a single setting entry by id */
    SettingGetById("settings/{0}", HttpMethods.GET, true),
    /** Set a single setting by its id */
    SettingSetById("settings/{0}", HttpMethods.POST, true),
    /** Retrieves the user information from the server. */
    UsersInfo("users.info", HttpMethods.GET, true),
    /** Retrieves a list of all the users in the server. */
    UsersList("users.list", HttpMethods.GET, true),
    /** Creates a new user. */
    UsersCreate("users.create", HttpMethods.POST, true),
    /** Updates a user. */
    UsersUpdate("users.update", HttpMethods.POST, true),
    /** Deletes a user. */
    UsersDelete("users.delete", HttpMethods.POST, true),
    /** Creates an auth token for an existing user**/
    UsersCreateToken("users.createToken",HttpMethods.POST, true);

    private String methodName;
    private HttpMethods httpMethod;
    private boolean requiresAuth;
    
    private RocketChatRestApiV1(String methodName, HttpMethods httpMethod, boolean requiresAuth) {
        this.methodName = methodName;
        this.httpMethod = httpMethod;
        this.requiresAuth = requiresAuth;
    }

    /**
     * Gets the method name to be called to the server.
     *
     * @return the method name plus "v1/" at the start
     */
    public String getMethodName() {
        return "v1/" + this.methodName;
    }

    /**
     * Gets the {@link HttpMethods http method} which should be used.
     *
     * @return {@link HttpMethods http method} to be used
     */
    public HttpMethods getHttpMethod() {
        return this.httpMethod;
    }

    /**
     * Check whether the method requires authentication or not.
     *
     * @return whether this requires authentication or not
     */
    public boolean requiresAuth() {
        return this.requiresAuth;
    }
}
