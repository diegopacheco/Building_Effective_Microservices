package com.packtpub.microservice.client;

import feign.Param;
import feign.RequestLine;

public interface MeetupMicroservice {
	
	@RequestLine("PUT /meetup?name={name}&type={type}")
	public boolean create(@Param("name")String name,@Param("type")String typez);
	
	@RequestLine("GET /meetup?type={type}")
	public MeetupsByType listByType(@Param("type")String typez);
	
}
