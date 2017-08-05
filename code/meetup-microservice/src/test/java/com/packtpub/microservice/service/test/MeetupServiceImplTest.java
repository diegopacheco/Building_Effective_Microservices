package com.packtpub.microservice.service.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.packtpub.microservice.dao.MeetupDAO;
import com.packtpub.microservice.service.Meetup;
import com.packtpub.microservice.service.MeetupService;
import com.packtpub.microservice.service.MeetupServiceImpl;

import rx.Observable;

public class MeetupServiceImplTest {

	@Test(expected=IllegalArgumentException.class)
	public void createNullMeetup() {
		MeetupDAO dao = mock(MeetupDAO.class);
		MeetupService service = new MeetupServiceImpl(dao);
		service.create(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createNullNameMeetup() {
		MeetupDAO dao = mock(MeetupDAO.class);
		MeetupService service = new MeetupServiceImpl(dao);
		Meetup m = new Meetup();
		m.setTypez("Tech");
		m.setName(null);
		service.create(m);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createNullTypeMeetup() {
		MeetupDAO dao = mock(MeetupDAO.class);
		MeetupService service = new MeetupServiceImpl(dao);
		Meetup m = new Meetup();
		m.setTypez(null);
		m.setName("Microservices");
		service.create(m);
	}
	
	@Test
	public void createMeetupOK() {
		MeetupDAO dao = mock(MeetupDAO.class);
		MeetupService service = new MeetupServiceImpl(dao);
		Meetup m = new Meetup();
		m.setTypez("Tech");
		m.setName("Microservices");
		service.create(m);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void listMeetupsByNullType() {
		MeetupDAO dao = mock(MeetupDAO.class);
		MeetupService service = new MeetupServiceImpl(dao);
		service.listByType(null);
	}
	
	@Test
	public void listMeetupsByTypeOK() {
		MeetupDAO dao = mock(MeetupDAO.class);
		when(dao.listByType("tech")).thenReturn(Observable.just(new HashSet<String>(Arrays.asList(new String[]{"tech"}))));
		
		MeetupService service = new MeetupServiceImpl(dao);
		
		Observable<Set<String>> ob = service.listByType("tech");
		Assert.assertNotNull(ob);
	}

}
