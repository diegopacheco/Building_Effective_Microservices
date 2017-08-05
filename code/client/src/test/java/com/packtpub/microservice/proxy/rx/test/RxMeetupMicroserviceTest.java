package com.packtpub.microservice.proxy.rx.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.packtpub.microservice.client.MeetupMicroservice;
import com.packtpub.microservice.client.MeetupsByType;
import com.packtpub.microservice.proxy.rx.MicroserviceProxyImpl;
import com.packtpub.microservice.proxy.rx.RxMeetupMicroservice;

import rx.Observable;

public class RxMeetupMicroserviceTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void createNullMeetup() {
		MeetupMicroservice proxy = mock(MeetupMicroservice.class);
		RxMeetupMicroservice service = new MicroserviceProxyImpl(proxy);
		service.create(null,null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createNullNameMeetup() {
		MeetupMicroservice proxy = mock(MeetupMicroservice.class);
		RxMeetupMicroservice service = new MicroserviceProxyImpl(proxy);
		service.create(null,"Tech");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createNullTypeMeetup() {
		MeetupMicroservice proxy = mock(MeetupMicroservice.class);
		RxMeetupMicroservice service = new MicroserviceProxyImpl(proxy);
		service.create("Microservices",null);
	}
	
	@Test
	public void createMeetupOK() {
		MeetupMicroservice proxy = mock(MeetupMicroservice.class);
		RxMeetupMicroservice service = new MicroserviceProxyImpl(proxy);
		service.create("Microservices","Tech");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void listMeetupsByNullType() {
		MeetupMicroservice proxy = mock(MeetupMicroservice.class);
		RxMeetupMicroservice service = new MicroserviceProxyImpl(proxy);
		service.listByType(null);
	}
	
	@Test
	public void listMeetupsByTypeOK() {
		MeetupMicroservice proxy = mock(MeetupMicroservice.class);
		when(proxy.listByType("Tech")).thenReturn(new MeetupsByType(new String[]{"Tech"}));
		
		RxMeetupMicroservice service = new MicroserviceProxyImpl(proxy);
		
		Observable<Set<String>> ob = service.listByType("Tech");
		Assert.assertNotNull(ob);
	}
	
}
