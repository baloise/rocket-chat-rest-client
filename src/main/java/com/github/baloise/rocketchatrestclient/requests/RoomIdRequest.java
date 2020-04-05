package com.github.baloise.rocketchatrestclient.requests;

public class RoomIdRequest {
	private String roomId;

	public RoomIdRequest(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * @return the roomId
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

}
