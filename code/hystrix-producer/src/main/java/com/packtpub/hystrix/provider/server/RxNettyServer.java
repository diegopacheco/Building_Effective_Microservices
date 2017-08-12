package com.packtpub.hystrix.provider.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import io.reactivex.netty.RxNetty;
import io.reactivex.netty.protocol.http.server.HttpServer;

@SuppressWarnings({"rawtypes"})
public class RxNettyServer {
	
	private Injector injector;
	private Integer port = 9090;
	private Module[] modules;
	private HttpServer server; 
	private RequrestAdapter adapter;
	
	public RxNettyServer withPort(Integer port){
		this.port = port;
		return this;
	}
	
	public RxNettyServer withModules(Module[] modules){
		this.modules = modules;
        return this;
	}
	
	public void start(){
		this.injector = Guice.createInjector(modules);
		this.adapter = injector.getInstance(RequrestAdapter.class);
		this.adapter.setInjector(injector);
		
		server = RxNetty.createHttpServer(port,
			(req, resp) -> adapter.handle(req, resp)
		);
		server.startAndWait();
	}
	
}
