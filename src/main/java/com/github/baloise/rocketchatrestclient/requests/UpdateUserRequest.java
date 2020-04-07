package com.github.baloise.rocketchatrestclient.requests;

/**
 * 
 * Class to update a Rocket.Chat user
 * 
 * @author Alfredo Quiroga
 *
 */
public class UpdateUserRequest {

	private String userId;
	private Data data;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the data
	 */
	public Data getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Data data) {
		this.data = data;
	}

	public static class Data {
		
		private String email;
		private String name;
		private String password;
		private boolean active;

		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}

		/**
		 * @return the active
		 */
		public boolean isActive() {
			return active;
		}

		/**
		 * @param active the active to set
		 */
		public void setActive(boolean active) {
			this.active = active;
		}

	}

}
