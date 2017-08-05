package com.packtpub.microservice.dao;

import java.util.Set;

import com.packtpub.microservice.service.Meetup;

import rx.Observable;

public interface MeetupDAO {
	
	public Observable<Void> create(Meetup m);
	
	public Observable<Set<String>> listByType(String typez);
	
}
