package com.packtpub.microservice.server;

import org.apache.log4j.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import io.reactivex.netty.protocol.http.server.HttpServer;
import rx.Observable;

@SuppressWarnings({"rawtypes","unchecked"})
public class RxNettyServer {
	
	private final static Logger logger = Logger.getLogger(RxNettyServer.class);
	
	private Injector injector;
	
	private Integer port = 9090;
	private Module[] modules;
	private HttpServer server; 
	
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
		server = HttpServer.newServer(port);
		server.start( (req, resp) -> resp.writeStringAndFlushOnEach( Observable.just("works"))).awaitShutdown();
	}
	
}
