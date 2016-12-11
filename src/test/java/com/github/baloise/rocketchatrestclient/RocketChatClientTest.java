package com.github.baloise.rocketchatrestclient;

import java.util.Date;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import com.github.baloise.rocketchatrestclient.model.Room;

public class RocketChatClientTest {

	@Test
	@Ignore
	public void test() throws Exception {
		String user = "";
		String password = "";
		String apiVersion = "/v1/";
		
		RocketChatClient rc = new RocketChatClient("http://localhost/api/", apiVersion ,user, password);

		// get meta info
		System.out.println("Api version is "+rc.getApiVersion());
		System.out.println("Rocket.Chat version is "+rc.getRocketChatVersion());

		
		// use typed API to retrieve rooms      
		Set<Room> rooms = rc.getPublicRooms();
		for (Room room : rooms) {
		    System.out.println(String.format("name: %s, id: %s", room.name, room._id));
		}
		
		//create a new channel
		rc.createChannel("test");

		// send a message to a room. Room ID is resolved automatically      
		rc.send("test", "Hello from REST client" + new Date());

		// no comment ;-)
		rc.logout();
	}

}
