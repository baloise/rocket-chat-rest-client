package com.github.baloise.rocketchatrestclient;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.baloise.rocketchatrestclient.model.Room;
import com.github.baloise.rocketchatrestclient.model.ServerInfo;
import com.github.baloise.rocketchatrestclient.model.User;

/**
 * This IT tests basic functionality of the client.
 *
 * @author Ryne Fagin (rynefagin)
 * @since 0.1.1
 * @version 0.0.1
 */
public class RocketChatClientTestIT {

	private static final String TEST_CASE_4 = "test0004";
	private static final String TEST_CASE_3 = "test0003";
	private static final String TEST_CASE_2 = "test0002";
	private static final String TEST_CASE_1 = "test0001";
	private static final String TEST_CASE_1234 = "test1234";
	private static final String TEST_CASE_0 = "test0000";
	
	String serverUrl = "http://localhost/api/";
	String user = "admin";
	String password = "supersecret";
	RocketChatClient rc = new RocketChatClient(serverUrl, user, password);

	@Test
	public void testRocketCatExists() throws Exception {

		ServerInfo info = rc.getServerInformation();
		assertTrue("The Rocket.Chat Version is empty, when it shouldn't be.", !info.getVersion().isEmpty());

		User rocketCat = rc.getUser("rocket.cat");
		assertTrue("The Rocket.Cat user's id doesn't match what it should be.", "rocket.cat".equals(rocketCat.getId()));

	}
	
	@Test
	public void testCreateAndCloseChannel() throws Exception{
		String roomNameTest = TEST_CASE_0;
		Room room = rc.createChannel(roomNameTest);
		assertTrue( "Room Id shouldn't be null if the room was created", (room.getId() !=null && !room.getId().isEmpty()));
	
		boolean isCalled = false;
		Room[] rooms = rc.getChannels();
		for(Room room1: rooms ){
			if(room1.getName().equals(roomNameTest))
			{
				rc.closeChannel(room.getId());
				isCalled = true;
				break;
			}
		}
		
		assertTrue("Error, channel did not exist for closing",isCalled);
	}
	
	@Test
	public void testCreateChannel() throws Exception {
		Room room = rc.createChannel(TEST_CASE_1234);
		assertTrue("Room Id shouldn't be null if the room was created",
				(room.getId() != null && !room.getId().isEmpty()));
	}
	
	@Test
	public void testCreateAndArchiveChannel() throws Exception{
		String roomNameTest = TEST_CASE_1;
		Room room = rc.createChannel(roomNameTest);
		assertTrue( "Room Id shouldn't be null if the room was created", (room.getId() !=null && !room.getId().isEmpty()));
	
		boolean isCalled = false;
		Room[] rooms = rc.getChannels();
		for(Room room1: rooms ){
			if(room1.getName().equals(roomNameTest))
			{
				rc.archiveChannel(room.getId());
				isCalled = true;
				break;
			}
		}
		
		assertTrue("Error, channel did not exist for archiving",isCalled);
	}
	
	@Test
	public void testCreateAndCloseGroup() throws Exception{
		String roomNameTest = TEST_CASE_2;
		Room room = rc.createGroup(roomNameTest);
		assertTrue( "Room Id shouldn't be null if the room was created", (room.getId() !=null && !room.getId().isEmpty()));
	
		boolean isCalled = false;
		Room[] rooms = rc.getGroups();
		for(Room room1: rooms ){
			if(room1.getName().equals(roomNameTest))
			{
				rc.closeGroup(room.getId());
				isCalled = true;
				break;
			}
		}
		
		assertTrue("Error, group did not exist for closing",isCalled);
	}
	
	@Test
	public void testCreateAndArchiveGroup() throws Exception{
		String roomNameTest = TEST_CASE_3;
		Room room = rc.createGroup(roomNameTest);
		assertTrue( "Room Id shouldn't be null if the room was created", (room.getId() !=null && !room.getId().isEmpty()));
	
		boolean isCalled = false;
		Room[] rooms = rc.getGroups();
		for(Room room1: rooms ){
			if(room1.getName().equals(roomNameTest))
			{
				rc.archiveGroup(room.getId());
				isCalled = true;
				break;
			}
		}
		
		assertTrue("Error, group did not exist for archiving",isCalled);
	}
	
	@Test
	public void testCreateAndGetGroup() throws Exception{
		String roomNameTest = TEST_CASE_4;
		Room room = rc.createGroup(roomNameTest);
		assertTrue( "Room Id shouldn't be null if the room was created", (room.getId() !=null && !room.getId().isEmpty()));
	
		Room room1 = rc.getGroupInfo(room.getId());
		
		assertTrue("Error, room was null", room1 != null);
		assertTrue("Error, group names were not equal", room1.getName().equals(roomNameTest));
	}
}
