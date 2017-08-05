package com.packtpub.microservice.guice;

import com.google.inject.AbstractModule;
import com.packtpub.microservice.dao.MeetupDAO;
import com.packtpub.microservice.dao.MeetupDAOImpl;
import com.packtpub.microservice.healthchecker.HealthcheckResource;
import com.packtpub.microservice.rest.MeetupResource;
import com.packtpub.microservice.service.MeetupService;
import com.packtpub.microservice.service.MeetupServiceImpl;

import netflix.karyon.health.HealthCheckHandler;

public class GuiceBindings extends AbstractModule{

	@Override
	protected void configure() {
		bind(HealthCheckHandler.class).toInstance(new HealthcheckResource());
		
		bind(MeetupResource.class).asEagerSingleton();
		bind(MeetupService.class).to(MeetupServiceImpl.class);
		bind(MeetupDAO.class).to(MeetupDAOImpl.class);
	}
	
}
