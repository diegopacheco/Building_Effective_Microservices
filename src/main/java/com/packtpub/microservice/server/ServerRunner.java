package com.packtpub.microservice.server;

import com.google.inject.Module;

import netflix.adminresources.resources.KaryonWebAdminModule;
import netflix.karyon.ShutdownModule;
import netflix.karyon.eureka.KaryonEurekaModule;
import netflix.karyon.servo.KaryonServoModule;

public class ServerRunner {
	
	public static void main(String[] args) {
		
		Module[] modules = new Module[]{
				//new ShutdownModule(), 
				//new KaryonWebAdminModule(), 
				//new KaryonServoModule(),
				//new KaryonEurekaModule() 
		};
		
		RxNettyServer server = new RxNettyServer();
		server.withPort(9090)
			  .withModules(modules)
			  .start();
		
	}
	
}
