package com.packtpub.microservice.dao;

import java.util.HashMap;
import java.util.Set;

import com.packtpub.microservice.service.Meetup;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import rx.Observable;

public class MeetupDAOImpl implements MeetupDAO{
	
	private JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
	
	@Override
	public Observable<Void> create(Meetup m) {
		
		try (Jedis jedis = pool.getResource()) {
			HashMap<String, String> meetups = new HashMap<>();
			meetups.put(m.getName(), m.getTypez());
			jedis.hmset(m.getTypez(), meetups);
		}
		return Observable.empty();
	}
	
	@Override
	public Observable<Set<String>> listByType(String typez){
		try (Jedis jedis = pool.getResource()) {
			return Observable.just(jedis.hkeys(typez) );
		}
	}
	
}
