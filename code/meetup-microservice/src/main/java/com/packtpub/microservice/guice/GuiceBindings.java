package com.packtpub.microservice.guice;

import com.google.inject.AbstractModule;
import com.netflix.config.ConfigurationManager;
import com.packtpub.microservice.dao.MeetupDAO;
import com.packtpub.microservice.dao.MeetupDAOImpl;
import com.packtpub.microservice.healthchecker.HealthcheckResource;
import com.packtpub.microservice.rest.MeetupResource;
import com.packtpub.microservice.server.RequrestAdapter;
import com.packtpub.microservice.server.RxNettyServiceAdapter;
import com.packtpub.microservice.service.MeetupService;
import com.packtpub.microservice.service.MeetupServiceImpl;

import netflix.karyon.health.HealthCheckHandler;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class GuiceBindings extends AbstractModule{

	@Override
	protected void configure() {
		bind(HealthCheckHandler.class).toInstance(new HealthcheckResource());
		
		bind(RequrestAdapter.class).to(RxNettyServiceAdapter.class);
		
		bind(MeetupResource.class).asEagerSingleton();
		bind(MeetupService.class).to(MeetupServiceImpl.class);
		bind(MeetupDAO.class).to(MeetupDAOImpl.class);

		bind(JedisPool.class).toInstance(
				new JedisPool(
						new JedisPoolConfig(), 
						 ConfigurationManager.getConfigInstance().getString("redis_ip","localhost")));
		
	}
	
}
