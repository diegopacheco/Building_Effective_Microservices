package com.packtpub.microservice.guice;

import com.google.inject.AbstractModule;
import com.packtpub.microservice.healthchecker.HealthcheckResource;

import netflix.karyon.health.HealthCheckHandler;

public class GuiceBindings extends AbstractModule{

	@Override
	protected void configure() {
		bind(HealthCheckHandler.class).toInstance(new HealthcheckResource());
	}
	
}
