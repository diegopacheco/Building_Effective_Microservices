package com.packtpub.microservice.proxy.ribbon;

import com.google.inject.Injector;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.governator.guice.LifecycleInjector;
import com.netflix.governator.guice.LifecycleInjectorBuilder;

public abstract class BaseRibbonTemplate {
	
	private EurekaClient eurekaClient;
	
	private EurekaClient getEurekaClient(){
		if (eurekaClient==null){
			LifecycleInjectorBuilder builder = LifecycleInjector.builder();
			Injector injector = builder.build().createInjector();
			eurekaClient = injector.getInstance(EurekaClient.class);
		}
		return eurekaClient;
	}
	
	public String getServerIP(String microservice){
		try{
			InstanceInfo info = getEurekaClient().getApplication(microservice.toUpperCase()).getInstances().get(0);
			String serverPort =  "http://" + info.getVIPAddress() + ":" + info.getPort();
			return serverPort;
		}catch(Exception e){
			throw new RuntimeException("Could not get Microservice IP:PORT. EX: " + e);
		}
	}
}
