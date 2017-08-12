package com.packtpub.hystrix.provider.server;

import com.google.inject.Module;
import com.packtpub.hystrix.provider.guice.GuiceBindings;

public class ServerRunner {
	
	public static void main(String[] args) {
		
		Module[] modules = new Module[]{
				new GuiceBindings(),
		};
		
		RxNettyServer server = new RxNettyServer();
		server.withPort(7070)
			  .withModules(modules)
			  .start();
		
	}
	
}
