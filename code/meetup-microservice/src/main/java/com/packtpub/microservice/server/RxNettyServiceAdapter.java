package com.packtpub.microservice.server;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.inject.Injector;
import com.netflix.hystrix.contrib.rxnetty.metricsstream.HystrixMetricsStreamHandler;
import com.packtpub.microservice.hystrix.SimpleCommand;
import com.packtpub.microservice.rest.MeetupResource;

import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import netflix.karyon.health.HealthCheckHandler;
import netflix.karyon.jersey.blocking.JerseyBasedRouter;
import rx.Observable;
import rx.functions.Func1;

@SuppressWarnings({"unchecked","rawtypes"})
public class RxNettyServiceAdapter extends JerseyBasedRouter implements RequrestAdapter{
	
	private final static Logger logger = Logger.getLogger(RxNettyServiceAdapter.class);
	private Injector injector;
	
	public Observable<Void> handle(HttpServerRequest request, HttpServerResponse response) {
		if (request.getPath().startsWith("/hystrix.stream")) {
			return new HystrixMetricsStreamHandler(this).handle(request, response);
		}
		return response.writeAndFlush(routeRequest(request, response).toBlocking().first().toString() );
	}
	
	private Observable routeRequest(HttpServerRequest req, HttpServerResponse resp) {
		Observable ob = null;
		
		if ("/favicon.ico".equals(req.getUri())) 
			return Observable.empty();
		
		if("/healthcheck".equals(req.getPath())){
			HealthCheckHandler healthChecker = injector.getInstance(HealthCheckHandler.class);
			ob = Observable.just( healthChecker.getStatus() );
			logger.info("Healthcehcker called");
			return ob;
		} 
		
		if ("/ops".equals(req.getPath())) {
			ob = new SimpleCommand("[ \"GET /meetup\", \"PUT /meetup\"]").toObservable();
			logger.info("Ops called");
			return ob;
		} 
		
		if ("/meetup".equals(req.getPath()) && HttpMethod.PUT.equals(req.getHttpMethod()) ){
			try{
				MeetupResource resource = injector.getInstance(MeetupResource.class);
				ob = resource.create( extractQueryParameter(req,"name"), 
									  extractQueryParameter(req,"type"));
				logger.info("Meetup called");
			}catch(IllegalArgumentException e){
				ob = Observable.empty();
				resp.setStatus(HttpResponseStatus.BAD_REQUEST);
			}catch(Exception e){
				ob = Observable.empty();
				resp.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
			}
			return ob;
		}  
		
		if ("/meetup".equals(req.getPath()) && HttpMethod.GET.equals(req.getHttpMethod()) ){
			try{
				MeetupResource resource = injector.getInstance(MeetupResource.class);
				ob = resource.listByType(extractQueryParameter(req, "type"));
				ob = transforSettoString(ob);
				logger.info("Meetup called");
			}catch(IllegalArgumentException e){
				ob = Observable.empty();
				resp.setStatus(HttpResponseStatus.BAD_REQUEST);
			}
			catch(Exception e){
				ob = Observable.empty();
				resp.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
			}	
			return ob;
		}
		
		ob = Observable.empty();
		logger.info("Unhandled method called: " + req.getPath());
		return ob;
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
