package com.packtpub.microservice.server;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.inject.Injector;
import com.packtpub.microservice.rest.MeetupResource;

import io.netty.handler.codec.http.HttpMethod;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.ResponseContentWriter;
import netflix.karyon.health.HealthCheckHandler;
import rx.Observable;
import rx.functions.Func1;

@SuppressWarnings({"unchecked","rawtypes"})
public class RxNettyServiceAdapter implements RequrestAdapter{
	
	private final static Logger logger = Logger.getLogger(RxNettyServiceAdapter.class);
	private Injector injector;
	
	@Override
	public ResponseContentWriter routeRequest(HttpServerRequest req, HttpServerResponse resp) {
		
		Observable ob = null;
		if("/healthcheck".equals(req.getDecodedPath())){
			HealthCheckHandler healthChecker = injector.getInstance(HealthCheckHandler.class);
			ob = Observable.just( healthChecker.getStatus() );
			logger.info("Healthcehcker called");
			
		} else if ("/meetup".equals(req.getDecodedPath()) && HttpMethod.PUT.equals(req.getHttpMethod()) ){

			MeetupResource resource = injector.getInstance(MeetupResource.class);
			ob = resource.create( extractQueryParameter(req,"name"), 
								  extractQueryParameter(req,"type"));
			logger.info("Meetup called");
			
		} else if ("/meetup".equals(req.getDecodedPath()) && HttpMethod.GET.equals(req.getHttpMethod()) ){

			MeetupResource resource = injector.getInstance(MeetupResource.class);
			ob = resource.listByType(extractQueryParameter(req, "type"));
			ob = transforSettoString(ob);
			logger.info("Meetup called");
			
		} else{
			ob = Observable.empty();
			logger.info("Unhandled method called.");
		}
		return resp.writeStringAndFlushOnEach(ob);
	}
	
	
	private Observable transforSettoString(Observable ob) {
		ob = ob.map(new Func1<Set<String>, String>() {
			public String call(Set<String> t) {
				Iterator<String> it = t.iterator();
				StringBuffer sb = new StringBuffer();
				sb.append("{ \"meetups\": [");
				while(it.hasNext()){
					sb.append("\"" + it.next() + "\"");
					if (it.hasNext()){
						sb.append(", ");
					}
				}
				sb.append("] }");
				return sb.toString();
			};	
		});
		return ob;
	}
	
	private String extractQueryParameter(HttpServerRequest req,String name){
		List<String> result = ((List<String>)req.getQueryParameters().get(name));
		return (result==null) ? null : result.get(0); 
	}

	public void setInjector(Injector injector) {
		this.injector = injector;
	}
}
