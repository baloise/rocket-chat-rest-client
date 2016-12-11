package com.github.baloise.rocketchatrestclient;

import java.util.HashMap;
import java.util.Map;

import com.github.baloise.rocketchatrestclient.model.Room;
import com.github.baloise.rocketchatrestclient.model.Version;

public class Cache {
	
	protected Map<String, Room> roomCache = new HashMap<>();
	protected Version version;

}
