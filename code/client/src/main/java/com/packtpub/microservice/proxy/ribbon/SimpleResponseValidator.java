package com.packtpub.microservice.proxy.ribbon;

import com.netflix.ribbon.ResponseValidator;
import com.netflix.ribbon.ServerError;
import com.netflix.ribbon.UnsuccessfulResponseException;

import io.reactivex.netty.protocol.http.client.HttpClientResponse;

@SuppressWarnings("rawtypes")
public class SimpleResponseValidator implements ResponseValidator<HttpClientResponse> {

	@Override
	public void validate(HttpClientResponse response) 
			throws UnsuccessfulResponseException,ServerError {
	}

}