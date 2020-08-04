package com.github.baloise.rocketchatrestclient;

import java.io.IOException;
import java.util.Date;

import com.github.baloise.rocketchatrestclient.model.Channel;
import com.github.baloise.rocketchatrestclient.model.Integration;
import com.github.baloise.rocketchatrestclient.model.Room;
import com.github.baloise.rocketchatrestclient.model.User;
import com.github.baloise.rocketchatrestclient.requests.ChannelHistoryRequest;
import com.github.baloise.rocketchatrestclient.requests.RoomAndUserRequest;

public class RocketChatRestApiV1Channels {
    private static final String ROOM_ID_PARAM_KEY = "roomId";
    private RocketChatClientCallBuilder callBuilder;

    protected RocketChatRestApiV1Channels(RocketChatClientCallBuilder callBuilder) {
        this.callBuilder = callBuilder;
    }

    /**
     * Adds <strong>all</strong> of the users of the Rocket.Chat server to the
     * channel.
     *
     * @param channel
     *            the channel of the room to add all users to
     * @return {@link Room} which is the channel
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public Channel addAllUsers(Channel channel) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsAddAll, null, channel);

        if (!res.isSuccessful())
            throw new IOException("The call to add all users to a channel was unsuccessful: \"" + res.getError() + "\"");

        if (!res.hasChannel())
            throw new IOException("The response does not contain any channel information.");

        return res.getChannel();
    }

    public void addModerator(Channel channel, User user) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsAddModerator, null, new RoomAndUserRequest(channel.getId(), user.getId()));

        if (!res.isSuccessful())
            throw new IOException("The call to add a moderator was unsuccessful: \"" + res.getError() + "\"");
    }

    public void addOwner(Channel channel, User user) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsAddOwner, null, new RoomAndUserRequest(channel.getId(), user.getId()));

        if (!res.isSuccessful())
            throw new IOException("The call tomessageReturn.channel add an owner was unsuccessful: \"" + res.getError() + "\"");
    }

    /**
     * Archives the channel
     *
     * @param channel
     *            the channel to archive
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public void archive(Channel channel) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsArchive, null, channel);

        if (!res.isSuccessful())
            throw new IOException("The call to archive the Channel was unsuccessful: \"" + res.getError() + "\"");
    }

    /**
     * Cleans up a channel, removing messages from the provided time range.
     *
     * @param channel
     *            the {@link Channel} of the room to clean the history of
     * @param latest
     *            end date of time range of messages to clean
     * @param oldest
     *            start date of time range of messages to clean
     * @param inclusive
     *            whether messages which land on latest and oldest should be
     *            included
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public void cleanHistory(Channel channel, Date latest, Date oldest, boolean inclusive) throws IOException {
        ChannelHistoryRequest request = new ChannelHistoryRequest(channel.getId());

        if (latest != null && oldest != null) {
            request.setLatest(latest);
            request.setOldest(oldest);
            request.setInclusive(inclusive);
        } else
            throw new IOException("latest and oldest must not be null");

        this.cleanHistory(request);
    }

    /**
     * Cleans up a channel, removing messages from the provided time range.
     *
     * @param historyParams
     *            the params to send with this request
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public void cleanHistory(ChannelHistoryRequest historyParams) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsCleanHistory, null, historyParams);

        if (!res.isSuccessful())
            throw new IOException("The call to clean the Channel was unsuccessful: \"" + res.getError() + "\"");
    }

    /**
     * Closes a channel, which hides it from the user's active list inside
     * Rocket.Chat
     *
     * @param channel
     *            the {@link Channel} of the room to close
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public void close(Channel channel) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsClose, null, channel);

        if (!res.isSuccessful())
            throw new IOException("The call to close the Channel was unsuccessful: \"" + res.getError() + "\"");
    }

    /**
     * Creates a new channel with any of the members added in the channel
     * defined.
     *
     * @param channel
     *            the {@link Channel} to create
     * @return the newly created {@link Channel}
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public Channel create(Channel channel) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsCreate, null, channel);

        if (!res.isSuccessful())
            throw new IOException("The call to create a Channel was unsuccessful: \"" + res.getError() + "\"");

        if (!res.hasChannel())
            throw new IOException("The response does not contain any channel information.");

        return res.getChannel();
    }

    /**
     * Deletes the channel.
     *
     * @param channel
     *            the {@link Channel} to delete
     * @return the deleted {@link Channel}
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public Channel delete(Channel channel) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsDelete, null, channel);

        if (!res.isSuccessful())
            throw new IOException("The call to delete a Channel was unsuccessful: \"" + res.getError() + "\"");

        if (!res.hasChannel())
            throw new IOException("The response does not contain any channel information.");

        return res.getChannel();
    }

    public Integration[] getIntegrations(Channel channel) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsGetIntegrations, new RocketChatQueryParams(ROOM_ID_PARAM_KEY, channel.getId()));

        if (!res.isSuccessful())
            throw new IOException("The call to get the Channel's Integrations was unsuccessful: \"" + res.getError() + "\"");

        if (!res.hasIntegrations())
            throw new IOException("The respone does no contain any integrations information.");

        return res.getIntegrations();
    }

    /**
     * Retrieves the information about the channel.
     *
     * @param channelId
     *            the "_id" of the room to get information
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     * @return The {@link Room} of the channel
     */
    public Channel info(String channelId) throws IOException {
        return this.info(new Channel(channelId, ""));
    }

    /**
     * Retrieves the information about the channel.
     *
     * @param channel
     *            the channel to get information for, must include an id
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     * @return The {@link Room} of the channel
     */
    public Channel info(Channel channel) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsInfo, new RocketChatQueryParams(ROOM_ID_PARAM_KEY, channel.getId()));

        if (!res.isSuccessful())
            throw new IOException("The call to get the Channel's information was unsuccessful: \"" + res.getError() + "\"");

        if (!res.hasChannel())
            throw new IOException("The call to get the Channel's information was unsuccessful, failed to return channel data.");

        return res.getChannel();
    }

    /**
     * Invites a user to a channel
     *
     * @param channel
     *            the {@link Channel} where the invite is coming from
     * @param user
     *            the {@link User} who is being invited
     * @return the {@link Channel} with new members array
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public Channel invite(Channel channel, User user) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsInvite, null, new RoomAndUserRequest(channel.getId(), user.getId()));

        if (!res.isSuccessful())
            throw new IOException("The call to invite a User to a Channel was unsuccessful: \"" + res.getError() + "\"");

        if (!res.hasChannel())
            throw new IOException("The call to invite a User to a Channel failed to return the channel data.");

        return res.getChannel();
    }

    public Channel join(Channel channel /* , String joinCode */) throws IOException {
        throw new IOException("Not implemented.");
    }

    /**
     * Removes a user from a channel
     *
     * @param channel
     *            the {@link Channel} where the kick is happening at
     * @param user
     *            the {@link User} who is being kicked
     * @return the {@link Channel} with new members array
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public Channel kick(Channel channel, User user) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsKick, null, new RoomAndUserRequest(channel.getId(), user.getId()));

        if (!res.isSuccessful())
            throw new IOException("The call to remove a User from a Channel was unsuccessful: \"" + res.getError() + "\"");

        if (!res.hasChannel())
            throw new IOException("The call to kick a User from a Channel failed to return the channel data.");

        return res.getChannel();
    }

    /**
     * Removes the callee from a channel
     *
     * @param channel
     *            the {@link Channel} of the room to leave
     * @return the {@link Channel} with the new information
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public Channel leave(Channel channel) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsLeave, null, channel);

        if (!res.isSuccessful())
            throw new IOException("The call to leave a Channel was unsuccessful: \"" + res.getError() + "\"");

        if (!res.hasChannel())
            throw new IOException("The call to leave a Channel failed to return the channel data.");

        return res.getChannel();
    }

	/**
	 * Convenience method to find a channel by its name
	 * 
	 * @param channelName
	 *            to search for
	 * @return the Channel if exactly one found, else null
	 * @throws IOException
	 *             on error
	 */
	public Channel findByName(String channelName) throws IOException{
		if (channelName != null) {
			RocketChatQueryParams queryParam =
				new RocketChatQueryParams("query", "{ \"name\": \"" + channelName + "\" }");
			Channel[] list = list(queryParam);
			if (list.length > 1) {
				throw new IOException(
					"Multiple channels with channelName " + channelName + " found.");
			}
			if (list.length == 1) {
				return list[0];
			}
		}
		return null;
	}
    
    /**
     * Gets the first number of the public channels from the Rocket.Chat server,
     * the amount depends on what the server has configured to return as the
     * default count with the default being 50.
     *
     * @return an array of {@link Room}s that are channels
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public Channel[] list() throws IOException {
        return this.list(new RocketChatQueryParams());
    }

    /**
     * Gets the public channels from the Rocket.Chat server, depending on the
     * query params provided otherwise uses the server's defaults.
     *
     * @param params
     *            an {@link RocketChatQueryParams} instance
     * @return an array of {@link Channel}s
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public Channel[] list(RocketChatQueryParams params) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsList, params);

        if (!res.isSuccessful())
            throw new IOException("The call to get the Public Channels was unsuccessful: \"" + res.getError() + "\"");

        if (!res.hasChannels())
            throw new IOException("Get Channels failed to retrieve the channels.");

        return res.getChannels();
    }

    public Channel[] listJoined(RocketChatQueryParams params) throws IOException {
        throw new IOException("list.joined not implemented yet.");
    }

    /**
     * Opens a channel, which makes it visible again in the user's active list
     * inside Rocket.Chat
     *
     * @param channel
     *            the {@link Channel} to open
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public void open(Channel channel) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsOpen, null, channel);

        if (!res.isSuccessful())
            throw new IOException("The call to open the Channel was unsuccessful: \"" + res.getError() + "\"");
    }

    public void removeModerator(Channel channel, User user) throws IOException {
        throw new IOException("Not Implemented.");
    }

    public void removeOwner(Channel channel, User user) throws IOException {
        throw new IOException("Not Implemented.");
    }

    /**
     * Renames the channel
     *
     * @param channel
     *            the {@link Channel} with the new name to rename the channel.
     * @return the new {@link Channel} information
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public Channel rename(Channel channel) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsRename, null, channel);

        if (!res.isSuccessful())
            throw new IOException("The call to rename a Channel was unsuccessful: \"" + res.getError() + "\"");

        if (!res.hasChannel())
            throw new IOException("The call to rename a Channel failed to return the channel data.");

        return res.getChannel();
    }

    public void setDescription(Channel channel) throws IOException {
        throw new IOException("Not Implemented.");
    }

    public void setJoinCode(Channel channel) throws IOException {
        throw new IOException("Not Implemented.");
    }

    public void setPurpose(Channel channel) throws IOException {
        throw new IOException("Not Implemented.");
    }

    public void setReadOnly(Channel channel) throws IOException {
        throw new IOException("Not Implemented.");
    }

    public void setTopic(Channel channel) throws IOException {
        throw new IOException("Not Implemented.");
    }

    public void setType(Channel channel) throws IOException {
        throw new IOException("Not Implemented.");
    }

    /**
     * Unarchives the channel
     *
     * @param channel
     *            the {@link Channel} to unarchive
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public void unarchiveChannel(Channel channel) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChannelsUnarchive, null, channel);

        if (!res.isSuccessful())
            throw new IOException("The call to unarchive the Channel was unsuccessful: \"" + res.getError() + "\"");
    }
}
