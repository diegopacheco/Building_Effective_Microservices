package com.packtpub.microservice.rest;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

@Singleton
@Path("/meetup")
public class MeetupResource {
	
	private final static Logger logger = Logger.getLogger(MeetupResource.class);
	
	@GET
	@Path("test")
	@Produces(MediaType.TEXT_PLAIN)
	public Response test() {
		try {
			return Response.ok( "Works"  ).
							build();
		} catch (Exception e) {
			logger.error("Error creating json response.", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
}
