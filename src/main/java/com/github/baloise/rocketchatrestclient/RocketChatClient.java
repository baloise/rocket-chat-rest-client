package com.github.baloise.rocketchatrestclient;

import static java.lang.String.format;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;

import com.github.baloise.rocketchatrestclient.model.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.baloise.rocketchatrestclient.model.Channel;
import com.github.baloise.rocketchatrestclient.model.Message;
import com.github.baloise.rocketchatrestclient.model.Room;
import com.github.baloise.rocketchatrestclient.model.Rooms;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RocketChatClient {

	private Configuration config;
	private Cache cache;
	private ObjectMapper jacksonObjectMapper;
	

	public RocketChatClient(String serverUrl, String apiVersionPrefix, String user, String password) {
		cache = new Cache();
		config = new Configuration(serverUrl, apiVersionPrefix, user, password);
		jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
		jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public Set<Room> getPublicRooms() throws IOException {
		Rooms rooms = authenticatedGet("publicRooms", Rooms.class, config);
		HashSet<Room> ret = new HashSet<>();
		cache.roomCache.clear();
		for (Room r : rooms.rooms) {
			ret.add(r);
			cache.roomCache.put(r.name, r);
		}
		return ret;
	}

	void login() throws UnirestException {
		HttpResponse<JsonNode> asJson = Unirest.post(config.getServerUrl() + "login").field("user", config.getUser())
				.field("password", config.getPassword()).asJson();
		if (asJson.getStatus() == 401) {
			throw new UnirestException("401 - Unauthorized");
		}
		JSONObject data = asJson.getBody().getObject().getJSONObject("data");
		config.setxAuthToken(data.getString("authToken"));
		config.setxUserId(data.getString("userId"));
	}

	public void logout() throws IOException {
		try {
			Unirest.post(config.getServerUrl() + "logout").header("X-Auth-Token", config.getxAuthToken())
					.header("X-User-Id", config.getxUserId()).asJson();
		} catch (UnirestException e) {
			throw new IOException(e);
		}
	}

	public String getApiVersion() throws IOException {
		if(cache.version == null)
			getVersion();
		
		return cache.version.getApi();
	}

	public String getRocketChatVersion() throws IOException {
		if(cache.version == null)
			getVersion();
		
		return cache.version.getRocketchat();
	}

	private void getVersion() throws IOException {

		if (cache.version == null) {
			try {
				JSONObject versionJSON = Get("version", config).getJSONObject("versions");
				Version version = new Version();
				version.setApi(versionJSON.getString("api"));
				version.setRocketchat(versionJSON.getString("rocketchat"));
				cache.version = version;

			} catch (IOException e) {
				throw new IOException(e);
			}
		}

	}
	
	public void createChannel(String channelName) throws IOException {
		if(!cache.roomCache.containsKey(channelName))
		{
			String method = config.getApiVersionPrefix()+API.CHANNEL_CREATE;
			authenticatedPost(method, new Channel(channelName), null, config);

		}
		this.getPublicRooms();
		
	}

	public void send(String roomName, String message) throws IOException {
		Room room = getRoom(roomName);
		if (room == null)
			throw new IOException(format("unknown room : %s", roomName));
		send(room, message);
	}

	public void send(Room room, String message) throws IOException {
		authenticatedPost("rooms/" + room._id + "/send", new Message(message), null, config);
	}

	public Room getRoom(String room) throws IOException {
		Room ret = cache.roomCache.get(room);
		if (ret == null) {
			getPublicRooms();
			ret = cache.roomCache.get(room);
		}
		return ret;
	}
	
	protected <T> T authenticatedPost(String method, Object request, Class<T> reponseClass, Configuration config) throws IOException {
		try {
			HttpResponse<String> ret = Unirest.post(config.getServerUrl() + method)
					.header("X-Auth-Token", config.getxAuthToken())
					.header("X-User-Id", config.getxUserId())
					.header("Content-Type", "application/json")
					.body(jacksonObjectMapper.writeValueAsString(request))
					.asString();
			if (ret.getStatus() == 401) {
				login();
				return authenticatedPost(method, request, reponseClass, config);
			}
			return reponseClass == null ? null : jacksonObjectMapper.readValue(ret.getBody(), reponseClass);
		} catch (UnirestException e) {
			throw new IOException(e);
		}
	}
	
	private <T> T Post(String method, Object request, Class<T> reponseClass, Configuration config) throws IOException {
		try {
			HttpResponse<String> ret = Unirest.post(config.getServerUrl() + method)
					.header("Content-Type", "application/json").body(jacksonObjectMapper.writeValueAsString(request))
					.asString();
			
			return reponseClass == null ? null : jacksonObjectMapper.readValue(ret.getBody(), reponseClass);
		} catch (UnirestException e) {
			throw new IOException(e);
		}
	}
	
	private JSONObject Put(String method, Configuration config) throws IOException {
		try {
			return Unirest.post(config.getServerUrl() + method).asJson().getBody().getObject();
			
		} catch (UnirestException e) {
			throw new IOException(e);
		}
	}
	
	private <T> T authenticatedGet(String method, Class<T> reponseClass, Configuration config) throws IOException {
		try {
			HttpResponse<String> ret = Unirest.get(config.getServerUrl() + method)
					.header("X-Auth-Token", config.getxAuthToken())
					.header("X-User-Id", config.getxUserId())
					.asString();
			if (ret.getStatus() == 401) {
				login();
				return authenticatedGet(method, reponseClass, config);
			}
			return jacksonObjectMapper.readValue(ret.getBody(), reponseClass);
		} catch (UnirestException e) {
			throw new IOException(e);
		}
	}
	
	private <T> T Get(String method, Class<T> reponseClass, Configuration config) throws IOException {
		try {
			HttpResponse<String> ret = Unirest.get(config.getServerUrl() + method)
					.asString();
			
			return jacksonObjectMapper.readValue(ret.getBody(), reponseClass);
		} catch (UnirestException e) {
			throw new IOException(e);
		}
	}
	
	private JSONObject Get(String method, Configuration config) throws IOException {
		try {
			return Unirest.get(config.getServerUrl() + method).asJson().getBody().getObject();
			
		} catch (UnirestException e) {
			throw new IOException(e);
		}
	}

}
