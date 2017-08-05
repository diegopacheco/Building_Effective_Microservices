package com.packtpub.microservice.healthchecker.test;

import org.junit.Assert;
import org.junit.Test;

import com.packtpub.microservice.healthchecker.HealthcheckResource;

import netflix.karyon.health.HealthCheckHandler;

public class HealthcheckResourceTest {
	
	@Test
	public void statusOK(){
		HealthCheckHandler  healthChecker = new HealthcheckResource();
		int status = healthChecker.getStatus();
		Assert.assertEquals(200, status);
	}
	
}
