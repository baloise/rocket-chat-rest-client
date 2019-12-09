package com.github.baloise.rocketchatrestclient;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.baloise.rocketchatrestclient.model.Channel;
import com.github.baloise.rocketchatrestclient.model.Group;
import com.github.baloise.rocketchatrestclient.model.Room;
import com.github.baloise.rocketchatrestclient.model.ServerInfo;
import com.github.baloise.rocketchatrestclient.model.Setting;
import com.github.baloise.rocketchatrestclient.model.User;
import com.github.baloise.rocketchatrestclient.util.TestConnectionInfo;

/**
 * This IT tests basic functionality of the client.
 *
 * @author Ryne Fagin (rynefagin)
 * @since 0.1.1
 * @version 0.0.1
 */
public class RocketChatClientTestIT {
    private static final String TEST_CASE_8 = "test0008";
    private static final String TEST_CASE_7 = "test0007";
    private static final String TEST_CASE_6 = "test0006";
    private static final String TEST_CASE_5 = "test0005";
    private static final String TEST_CASE_4 = "test0004";
    private static final String TEST_CASE_3 = "test0003";
    private static final String TEST_CASE_2 = "test0002";
    private static final String TEST_CASE_1 = "test0001";
    private static final String TEST_CASE_1234 = "test1234";
    private static final String TEST_CASE_0 = "test0000";

    private RocketChatClient rc;

    @Before
    public void setUp() throws Exception {
        this.rc = new RocketChatClient(TestConnectionInfo.ServerUrl, TestConnectionInfo.User, TestConnectionInfo.Password);

        assertNotNull("An error occured setting up Rocket.Chat Client, it is null.", this.rc);
    }

    @After
    public void tearDown() throws Exception {
        if (this.rc == null)
            return;

        // TODO: Clean up/delete the channels/groups/rooms/etc
    }

    @Test
    public void testRocketCatExists() throws Exception {

        ServerInfo info = this.rc.getServerInformation();
        assertFalse("The Rocket.Chat Version is empty, when it shouldn't be.", info.getVersion().isEmpty());

        User rocketCat = this.rc.getUsersApi().getInfo("rocket.cat");
        assertTrue("The Rocket.Cat user's id doesn't match what it should be.", "rocket.cat".equals(rocketCat.getId()));
    }

    @Test
    public void testCreateCloseAndOpenChannel() throws Exception {
        String roomNameTest = TEST_CASE_0;
        Channel channel = this.rc.getChannelsApi().create(new Channel(roomNameTest));
        assertTrue("Room Id shouldn't be null if the room was created", channel.getId() != null && !channel.getId().isEmpty());

        this.rc.getChannelsApi().close(channel);
        this.rc.getChannelsApi().open(channel);
    }

    @Test
    public void testCreateChannel() throws Exception {
        Room room = this.rc.getChannelsApi().create(new Channel(TEST_CASE_1234));
        assertTrue("Room Id shouldn't be null if the room was created", room.getId() != null && !room.getId().isEmpty());
        assertTrue("Room name wasn't created with the provided name.", room.getName().equals(TEST_CASE_1234));
    }

    @Test
    public void testCreateArchiveAndUnarchiveChannel() throws Exception {
        String roomNameTest = TEST_CASE_1;
        Channel room = this.rc.getChannelsApi().create(new Channel(roomNameTest));
        assertTrue("Room Id shouldn't be null if the room was created", (room.getId() != null && !room.getId().isEmpty()));

        this.rc.getChannelsApi().archive(room);
        room = this.rc.getChannelsApi().info(room);
        assertTrue("The channel should be archived, but it wasn't.", room.isArchived());

        this.rc.getChannelsApi().unarchiveChannel(room);
        room = this.rc.getChannelsApi().info(room);
        assertFalse("The channel shouldn't be archived, but it is.", room.isArchived());
    }

    @Test
    public void testCreateCloseAndOpenGroup() throws Exception {
        Group group = this.rc.getGroupsApi().create(new Group(TEST_CASE_2));
        assertTrue("Room Id shouldn't be null if the room was created", (group.getId() != null && !group.getId().isEmpty()));

        this.rc.getGroupsApi().close(group);
    }

    @Test
    public void testCreateArchiveAndUnarchiveGroup() throws Exception {
        Group group = this.rc.getGroupsApi().create(new Group(TEST_CASE_3));
        assertTrue("Room Id shouldn't be null if the room was created", (group.getId() != null && !group.getId().isEmpty()));

        this.rc.getGroupsApi().archive(group);
        group = this.rc.getGroupsApi().info(group);
        assertTrue("The group should be archived, but it wasn't.", group.isArchived());

         this.rc.getGroupsApi().unarchive(group);
         group = this.rc.getGroupsApi().info(group);
         assertFalse("The group shouldn't be archived, but it is.", group.isArchived());
    }

    @Test
    public void testCreateAndGetGroup() throws Exception {
        Group room = this.rc.getGroupsApi().create(new Group(TEST_CASE_4));
        assertTrue("Room Id shouldn't be null if the room was created", room.getId() != null && !room.getId().isEmpty());

        Group room1 = this.rc.getGroupsApi().info(room.getId());

        assertTrue("Error, room was null", room1 != null);
        assertEquals("Error, group names were not equal", room1.getName(), TEST_CASE_4);
    }

    @Test
    public void testRenameChannel() throws Exception {
        String roomNameTest = TEST_CASE_5;
        Channel channel = this.rc.getChannelsApi().create(new Channel(roomNameTest));
        assertTrue("Room Id shouldn't be null if the room was created", channel.getId() != null && !channel.getId().isEmpty());
        assertEquals(TEST_CASE_5, channel.getName());

        channel.setName(TEST_CASE_6);

        channel = this.rc.getChannelsApi().rename(channel);
        assertEquals(TEST_CASE_6, channel.getName());
    }

    @Test
    public void testRenameGroup() throws Exception {
        Group group = this.rc.getGroupsApi().create(new Group(TEST_CASE_7));
        assertTrue("Room Id shouldn't be null if the room was created", group.getId() != null && !group.getId().isEmpty());
        assertEquals(TEST_CASE_7, group.getName());
        
        group.setName(TEST_CASE_8);

        group = this.rc.getGroupsApi().rename(group);
        assertEquals(TEST_CASE_8, group.getName());
    }
    
	@Test
	public void testGetSettingById() throws Exception{
		Setting setting = this.rc.getSettingsApi().getById("Organization_Name");
		assertEquals("Organization_Name", setting.getId());
		assertEquals("", setting.getValue());
	}
	
	@Test
	public void testSetSettingByIdString() throws Exception{
		this.rc.getSettingsApi().setById("Organization_Name", "TestOrganizationName");
		Setting setting = this.rc.getSettingsApi().getById("Organization_Name");
		assertEquals("TestOrganizationName", setting.getValue());
	}

	@Test
	public void testSetSettingByIdStringWithEmptyValue() throws IOException {
		this.rc.getSettingsApi().setById("Accounts_RegistrationForm_LinkReplacementText", "");
		Setting setting = this.rc.getSettingsApi().getById("Accounts_RegistrationForm_LinkReplacementText");
		assertEquals("", setting.getValue());
	}
	
	@Test
	public void testSetSettingByIdBoolean() throws Exception{
		this.rc.getSettingsApi().setById("SAML_Custom_Default", Boolean.TRUE);
		Setting setting = this.rc.getSettingsApi().getById("SAML_Custom_Default");
		assertEquals(Boolean.TRUE, setting.getValue());
	}
	
	@Test
	public void testListSettings() throws Exception{
		Setting[] settings = this.rc.getSettingsApi().list();
		assertNotNull(settings);
		List<Setting> _settings = Arrays.asList(settings);
		Setting organizationNameSetting = _settings.stream()
			.filter(e -> "Organization_Name".equals(e.getId())).findFirst().orElse(null);
		assertNotNull(organizationNameSetting);
	}
}
