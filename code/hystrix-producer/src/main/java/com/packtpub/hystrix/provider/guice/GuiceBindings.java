package com.packtpub.hystrix.provider.guice;

import com.google.inject.AbstractModule;
import com.packtpub.hystrix.provider.healthchecker.HealthcheckResource;
import com.packtpub.hystrix.provider.rest.CalculatorResource;
import com.packtpub.hystrix.provider.server.RequrestAdapter;
import com.packtpub.hystrix.provider.server.RxNettyServiceAdapter;
import com.packtpub.hystrix.provider.service.CalculatorService;
import com.packtpub.hystrix.provider.service.CalculatorServiceImpl;

import netflix.karyon.health.HealthCheckHandler;

public class GuiceBindings extends AbstractModule{

	@Override
	protected void configure() {
		bind(HealthCheckHandler.class).toInstance(new HealthcheckResource());
		bind(RequrestAdapter.class).to(RxNettyServiceAdapter.class);
		bind(CalculatorResource.class).asEagerSingleton();
		bind(CalculatorService.class).to(CalculatorServiceImpl.class);
	}
	
}
