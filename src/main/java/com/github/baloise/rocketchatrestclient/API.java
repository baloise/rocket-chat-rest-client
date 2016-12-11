package com.github.baloise.rocketchatrestclient;

public final class API {

	public static final String CHANNEL_ADD_ALL = "channels.addAll";
	public static final String CHANNEL_ARCHIVE = "channels.archive";
	public static final String CHANNEL_CLEAN_HISTORY = "channels.cleanHistory";
	public static final String CHANNEL_CLOSE = "channels.close";
	public static final String CHANNEL_CREATE = "channels.create";
	public static final String CHANNEL_HISTORY = "channels.history";
	public static final String CHANNEL_INFO = "channels.info";
	public static final String CHANNEL_INVITE = "channels.invite";
	public static final String CHANNEL_KICK = "channels.kick";
	public static final String CHANNEL_LEAVE = "channels.leave";
	public static final String CHANNEL_LIST = "channels.list";
	public static final String CHANNEL_LIST_JOINTED = "channels.list.joined";
	public static final String CHANNEL_OPEN = "channels.open";
	public static final String CHANNEL_RENAME = "channels.rename";
	public static final String CHANNEL_SET_DESCRIPTION = "channels.setDescription";
	public static final String CHANNEL_SET_PURPOSE = "channels.setPurpose";
	public static final String CHANNEL_SET_TOPIC = "channels.setTopic";
	public static final String CHANNEL_UNARCHIVE = "channels.unarchive";
	
	public static final String GROUP_ARCHIVE = "groups.archive"; //Archives a private group.
	public static final String GROUP_CLASS = "groups.closs"; //Removes a private group from the list of groups.
	public static final String GROUP_CREATE = "groups.create"; //Creates a new private group.
	public static final String GROUP_HISTORY = "groups.history"; //Retrieves the messages from a private group.
	public static final String GROUP_INFO = "groups.info"; //Gets the information about a private group.
	public static final String GROUP_INVITE = "groups.invite"; //Adds a user to the private group.
	public static final String GROUP_KICK = "groups.kick"; //Removes a user from a private group.
	public static final String GROUP_LEAVE = "groups.leave"; //Removes the calling user from the private group.
	public static final String GROUP_LIST = "groups.list"; //List the private groups the caller is part of.
	public static final String GROUP_OPEN = "groups.open"; //Adds the private group back to the list of groups.
	public static final String GROUP_RENAME = "groups.rename"; //Changes the name of the private group.
	public static final String GROUP_SET_DESCRIPTION = "groups.setDescription"; //Sets a private group’s description.
	public static final String GROUP_SET_PURPOSE = "groups.setPurpose"; //Sets a private group’s description.
	public static final String GROUP_SET_TOPIC = "groups.setTopic"; //Sets a private group’s topic.
	public static final String GROUP_UNARCHIVE = "groups.unarchive"; //Unarchives a private group.

}
