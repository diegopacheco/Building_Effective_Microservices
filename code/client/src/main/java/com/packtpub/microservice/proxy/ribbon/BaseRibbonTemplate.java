package com.packtpub.microservice.proxy.ribbon;

import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Injector;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.governator.guice.LifecycleInjector;
import com.netflix.governator.guice.LifecycleInjectorBuilder;

public abstract class BaseRibbonTemplate {
	
	private EurekaClient eurekaClient;
	protected ObjectMapper mapper = new ObjectMapper();
	
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
			List<InstanceInfo> infos = getEurekaClient().getApplication(microservice.toUpperCase()).getInstances();
			Iterator<InstanceInfo> it = infos.iterator();
			StringBuffer ips = new StringBuffer();
			
			while(it.hasNext()){
				InstanceInfo info = it.next();
				String serverPort =  "http://" + info.getIPAddr() + ":" + info.getPort();
				ips.append(serverPort);
				if (it.hasNext())
					ips.append(",");
			}
			return ips.toString();
		}catch(Exception e){
			throw new RuntimeException("Could not get Microservice IP:PORT. EX: " + e);
		}
	}
}
