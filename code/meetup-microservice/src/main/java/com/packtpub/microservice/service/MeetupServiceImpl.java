package com.packtpub.microservice.service;

import java.util.Set;

import javax.inject.Inject;

import com.packtpub.microservice.dao.MeetupDAO;

import rx.Observable;

public class MeetupServiceImpl implements MeetupService {
	
	private MeetupDAO dao;
	
	@Inject
	public MeetupServiceImpl(MeetupDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public Observable<Set<String>> listByType(String typez){
		if(null==typez || "".equals(typez)) 
			throw new IllegalArgumentException("Meeutp type cannot be null");
		return dao.listByType(typez);
	}
	
	@Override
	public Observable<Boolean> create(Meetup m) {
		validate(m);
		return dao.create(m);
	}

	private void validate(Meetup m) {
		if(m==null) throw new IllegalArgumentException("Meeutp cannot be null");
		if(null==m.getName() ||  "".equals(m.getName())) throw new IllegalArgumentException("Meeutp name cannot be null");
		if(null==m.getTypez() || "".equals(m.getTypez())) throw new IllegalArgumentException("Meeutp type cannot be null");
	}
	
}
