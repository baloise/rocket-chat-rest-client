package com.github.baloise.rocketchatrestclient;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.github.baloise.rocketchatrestclient.model.Room;
import com.github.baloise.rocketchatrestclient.model.ServerInfo;
import com.github.baloise.rocketchatrestclient.model.User;

/**
 * Client for Rocket.Chat which relies on the REST API v1.
 * <p>
 * Please note, this client does <strong>not</strong> cache any of the results.
 *
 * @version 0.1.0
 * @since 0.0.1
 */
public class RocketChatClient {
    private RocketChatClientCallBuilder callBuilder;
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");

    /**
     * Initialize a new instance of the client providing the server's url along with username and
     * password to use.
     *
     * @param serverUrl of the Rocket.Chat server, with or without it ending in "/api/"
     * @param user which to authenticate with
     * @param password of the user to authenticate with
     */
    public RocketChatClient(String serverUrl, String user, String password) {
        this.callBuilder = new RocketChatClientCallBuilder(serverUrl, user, password);
    }

    /**
     * Forces a logout and clears the auth token if no exception happened.
     *
     * @throws IOException is thrown if there was a problem connecting, including if the result
     *             wasn't successful
     */
    public void logout() throws IOException {
        this.callBuilder.logout();
    }
    
    /**
     * Gets the {@link ServerInfo} from the server, containing the version.
     * 
     * @return the {@link ServerInfo}
     * @throws IOException is thrown if there was a problem connecting, including if the result
     *             wasn't successful
     */
    public ServerInfo getServerInformation() throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.Info);
        
        if (!res.isSuccessful())
            throw new IOException("The call out to get the server information was unsuccessful.");
        
        if (!res.hasServerInfo())
            throw new IOException("The server information was not retrieved from the server.");
        
        return res.getServerInfo();
    }

    /**
     * Gets <strong>all</strong> of the users from a Rocket.Chat server, if you have a ton this will
     * take some time.
     *
     * @return an array of {@link User}s
     * @throws IOException is thrown if there was a problem connecting, including if the result
     *             wasn't successful
     */
    public User[] getUsers() throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.UsersList);

        if (!res.isSuccessful())
            throw new IOException("The call to get the Users was unsuccessful.");

        if (!res.hasUsers())
            throw new IOException("Get User Information failed to retrieve the users.");

        return res.getUsers();
    }

    /**
     * Retrieves a {@link User} from the Rocket.Chat server.
     *
     * @param userId of the user to retrieve
     * @return an instance of the {@link User}
     * @throws IOException is thrown if there was a problem connecting, including if the result
     *             wasn't successful or there is no user
     */
    public User getUser(String userId) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.UsersInfo, new RocketChatQueryParams("userId", userId));

        if (!res.isSuccessful())
            throw new IOException("The call to get the User's Information was unsuccessful.");

        if (!res.hasUser())
            throw new IOException("Get User Information failed to retrieve a user.");

        return res.getUser();
    }

    /**
     * Gets <strong>all</strong> of the public channels from a Rocket.Chat server, if you have a ton
     * this will take some time.
     *
     * @return an array of {@link Room}s that are channels
     * @throws IOException is thrown if there was a problem connecting, including if the result
     *             wasn't successful
     */
    public Room[] getChannels() throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsList);

        if (!res.isSuccessful())
            throw new IOException("The call to get the Public Channels was unsuccessful.");

        if (!res.hasChannels())
            throw new IOException("Get Channels failed to retrieve the channels.");

        return res.getChannels();
    }
    
    /**
     * Retrieves information from the server about the channel.
     * 
     * @param channelId the "_id" of the channel to get
     * @return the {@link Room} which is the channel
     * @throws IOException is thrown if there was a problem connecting, including if the result
     *             wasn't successful
     */
    public Room getChannel(String channelId) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsInfo, new RocketChatQueryParams("roomId", channelId));
        
        if (!res.isSuccessful())
            throw new IOException("The call to get the Channel's Information was unsuccessful.");
        
        if (!res.hasChannel())
            throw new IOException("The response does not contain any channel information.");
        
        return res.getChannel();
    }
    
	/**
	 * Creates a new channel with only the creator added
	 * 
	 * @param channelName
	 *            the of the channel to create
	 * @return the {@link Room} which is the newly created channel
	 * @throws IOException
	 *             is thrown if there was a problem connecting, including if the
	 *             result wasn't successful
	 */
	public Room createChannel(String channelName) throws IOException {

		Room room = new Room();
		room.setName(channelName);
		
		RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsCreate, null, room);

		if (!res.isSuccessful())
			throw new IOException("The call to create a Channel was unsuccessful.");

		if (!res.hasChannel())
			throw new IOException("The response does not contain any channel information.");

		return res.getChannel();
	}
	
	 /**
     * Adds <strong>all</strong> of the users of the Rocket.Chat server to the channel.
     * 
     * @param channelId 	the "_id" of the room to add all users to
     * @return {@link Room} which is the channel
     * @throws IOException is thrown if there was a problem connecting, including if the result
     *             wasn't successful
     */
    public Room addAllChannel(String channelId) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsAddAll, new RocketChatQueryParams("roomId", channelId));
        
        if (!res.isSuccessful())
            throw new IOException("The call to get the Channel's Information was unsuccessful.");
        
        if (!res.hasChannel())
            throw new IOException("The response does not contain any channel information.");
        
        return res.getChannel();
    }
    
    /**
     * Archives a channel
     * 
     * @param channelId 	the "_id" of the room to add all users to
     * @throws IOException 	is thrown if there was a problem connecting, including if the result
     *             wasn't successful
     */
    public void archiveChannel(String channelId) throws IOException {
    	Map<String,Object> qp = new HashMap<String,Object>();
    	qp.put("roomId", channelId);
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsArchive, null, qp);
        
        if (!res.isSuccessful())
            throw new IOException("The call to archive the Channel was unsuccessful.");
        
    }
    
    /**
     * Cleans up a channel, removing messages from the provided time range.
     * 
     * @param channelId 	the "_id" of the room to clean the history of
     * @param latest 		{@link java.util.Date} end date of time range of messages to clean
     * @param oldest 		{@link java.util.Date} start date of time range of messages to clean
     * @param inclusive 	{@link java.lang.Boolean} whether messages which land on latest and oldest should be included
     * @throws IOException 	is thrown if there was a problem connecting, including if the result
     *             wasn't successful
     */
    public void cleanHistoryOfChannel(String channelId, Date latest, Date oldest, Boolean inclusive) throws IOException {
        
    	Map<String,Object> rcqp = new HashMap<String,Object>();
    	rcqp.put("roomId", channelId);
    	
    	if(latest!=null && oldest!=null && inclusive!=null)
    	{
    		rcqp.put("latest", df.format(latest));
    		rcqp.put("oldest", df.format(oldest));
    		rcqp.put("inclusive", inclusive);
    	}else{
    		throw new IOException("latest, oldest and inclusive must not be null");
    	}
    	
    	RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsCleanHistory, null, rcqp);
        
        if (!res.isSuccessful())
            throw new IOException("The call to clean the Channel was unsuccessful.");
        
    }
    
    /**
     * Closes a channel
     * 
     * @param channelId the "_id" of the room to add all users to
     * @throws IOException is thrown if there was a problem connecting, including if the result
     *             wasn't successful
     */
    public void closeChannel(String channelId) throws IOException {
    	Map<String, Object> bodyMap = new HashMap<String,Object>();
    	bodyMap.put("roomId", channelId);
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsClose, null, (new HashMap<String,Object>()).put("roomId", channelId) );
        
        if (!res.isSuccessful())
            throw new IOException("The call to close the Channel was unsuccessful.");
        
    }
    
    /**
     * Retrieves the information about the channel.
     * 
     * @param channelId 	the "_id" of the room to get information
     * @throws IOException 	is thrown if there was a problem connecting, including if the result
     *             wasn't successful
     * @returns The {$link Room} of the channel
     */
    public Room getChannelInfo(String channelId) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsInfo, 
        		new RocketChatQueryParams("roomId", channelId));
        
        if (!res.isSuccessful())
            throw new IOException("The call to get the Channel's information failed.");
        
        if (!res.hasChannel())
            throw new IOException("The call to get the Channel's information failed.");
        
        return res.getChannel();
        
    }
    
	
}
