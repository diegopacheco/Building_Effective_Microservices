package com.packtpub.microservice.client;

import java.util.Arrays;

public class MeetupsByType {
	public String[] meetups;
	
	@Override
	public String toString() {
		return Arrays.toString(meetups);
	}
}
