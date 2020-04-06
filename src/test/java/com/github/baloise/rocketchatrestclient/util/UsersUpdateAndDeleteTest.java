package com.github.baloise.rocketchatrestclient.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.github.baloise.rocketchatrestclient.RocketChatClient;
import com.github.baloise.rocketchatrestclient.model.User;
import com.github.baloise.rocketchatrestclient.requests.CreateUserRequest;
import com.github.baloise.rocketchatrestclient.requests.UpdateUserRequest;
import com.github.baloise.rocketchatrestclient.requests.UpdateUserRequest.Data;
import com.github.baloise.rocketchatrestclient.requests.UserIdRequest;

public class UsersUpdateAndDeleteTest {

	private static final String USER_ROLE = "user";
	private static final String TEST_USER_FIRSTNAME = "Test";
	private static final String TEST_USER_EMAIL = "test@example.com";
	private static final String TEST_USERNAME = "test";
	private static final String TEST_USER_PASSWORD = "test_user_password";
	private static final String TEST_USER_NEW_PASSWORD = "test_user_new_password";

	@Test
	public void testRocketChat() {

		try {
			/**
			 * Connect to messaging gateway
			 */
			RocketChatClient adminAndOriginatorClient = new RocketChatClient(TestConnectionInfo.ServerUrl,
					TestConnectionInfo.User, TestConnectionInfo.Password);
			assertNotNull(adminAndOriginatorClient);

			/**
			 * Create a user
			 */
			User[] users = adminAndOriginatorClient.getUsersApi().list();
			User user = null;

			for (User rocketUser : users) {
				if (TEST_USERNAME.equals(rocketUser.getUsername())) {
					user = rocketUser;
					break;
				}
			}

			if (user == null) {
				final CreateUserRequest createUserRequest = new CreateUserRequest(TEST_USER_EMAIL, TEST_USER_FIRSTNAME,
						TEST_USER_PASSWORD, TEST_USERNAME, true, new String[] { USER_ROLE });
				user = adminAndOriginatorClient.getUsersApi().create(createUserRequest);
			}
			assertNotNull(user.getId());

			/**
			 * Update a user (password)
			 */
			final UpdateUserRequest updateUserRequest = new UpdateUserRequest();

			final Data data = new UpdateUserRequest.Data();
			data.setActive(true);
			data.setPassword(TEST_USER_NEW_PASSWORD);

			updateUserRequest.setUserId(user.getId());
			updateUserRequest.setData(data);

			final User updatedUser = adminAndOriginatorClient.getUsersApi().update(updateUserRequest);

			assertNotNull(updatedUser);
			final RocketChatClient testClient = new RocketChatClient(TestConnectionInfo.ServerUrl, TEST_USERNAME,
					TEST_USER_NEW_PASSWORD);
			assertNotNull(testClient);

			/**
			 * Delete a user
			 */
			final boolean deleted = adminAndOriginatorClient.getUsersApi()
					.deleteByUserIdUserId((new UserIdRequest("", user.getId())));
			assertTrue(deleted);

		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
