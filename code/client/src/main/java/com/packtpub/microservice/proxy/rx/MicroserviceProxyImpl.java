package com.packtpub.microservice.proxy.rx;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.packtpub.microservice.client.MeetupMicroservice;

import feign.Feign;
import feign.gson.GsonDecoder;
import rx.Observable;

public class MicroserviceProxyImpl implements RxMeetupMicroservice{
	
	private MeetupMicroservice proxy;
	
	public MicroserviceProxyImpl() {
		proxy = Feign.builder().
				decoder(new GsonDecoder()).
				target(MeetupMicroservice.class,"http://localhost:9090/");
	}
	
	@Override
	public Observable<Void> create(String name, String typez) {
		proxy.create(name, typez);
		return Observable.empty();
	}
	
	@Override
	public Observable<Set<String>> listByType(String typez) {
		String[] rawResult = proxy.listByType(typez).meetups;
		Set<String> set = new HashSet<String>(Arrays.asList(rawResult));
		return Observable.just(set);
	}
	
	public static void main(String[] args) {
		MicroserviceProxyImpl proxy = new MicroserviceProxyImpl();
		proxy.create("Pizza", "cook");
		proxy.listByType("cook").forEach( (t) -> System.out.println(t)); 
	}
}
