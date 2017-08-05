package com.packtpub.microservice.server;

import com.google.inject.Module;
import com.packtpub.microservice.guice.GuiceBindings;

public class ServerRunner {
	
	public static void main(String[] args) {
		
		Module[] modules = new Module[]{
				new GuiceBindings(),
				//new KaryonEurekaModule() 
		};
		
		RxNettyServer server = new RxNettyServer();
		server.withPort(9090)
			  .withModules(modules)
			  .start();
		
	}
	
}
