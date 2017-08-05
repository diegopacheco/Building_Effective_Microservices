package com.packtpub.microservice.server;

import com.google.inject.Injector;

import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.ResponseContentWriter;

@SuppressWarnings("rawtypes")
public interface RequrestAdapter {
	
	public ResponseContentWriter routeRequest(HttpServerRequest req,HttpServerResponse resp);
	
	public void setInjector(Injector injector);
}
