package com.packtpub.microservice.server;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Module;
import com.netflix.config.ConfigurationManager;
import com.packtpub.microservice.guice.GuiceBindings;

import netflix.karyon.eureka.KaryonEurekaModule;

public class ServerRunner {
	
	public static void main(String[] args) {
		
		List<Module> modules = new ArrayList<>();
		modules.add(new GuiceBindings());

		if (ConfigurationManager.getConfigInstance().getBoolean("eureka_registry",true))
			modules.add(new KaryonEurekaModule()); 
		
		RxNettyServer server = new RxNettyServer();
		server.withPort(9090)
			  .withModules(modules.toArray(new Module[modules.size()]))
			  .start();
		
	}
	
}
