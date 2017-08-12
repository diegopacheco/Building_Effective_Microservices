package com.packtpub.hystrix.provider.server;

import com.google.inject.Injector;

import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import rx.Observable;

@SuppressWarnings("rawtypes")
public interface RequrestAdapter {
	
	public Observable<Void> handle(HttpServerRequest request, HttpServerResponse response);
	
	public void setInjector(Injector injector);
}
