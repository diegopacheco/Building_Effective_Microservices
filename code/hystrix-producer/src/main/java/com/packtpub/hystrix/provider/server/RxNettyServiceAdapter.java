package com.packtpub.hystrix.provider.server;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.inject.Injector;
import com.netflix.hystrix.contrib.rxnetty.metricsstream.HystrixMetricsStreamHandler;
import com.packtpub.hystrix.provider.rest.CalculatorResource;

import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import netflix.karyon.health.HealthCheckHandler;
import netflix.karyon.jersey.blocking.JerseyBasedRouter;
import rx.Observable;

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
		
		if (req.getPath().startsWith("/calc/sum") && HttpMethod.GET.equals(req.getHttpMethod()) ){
			try{
				CalculatorResource resource = injector.getInstance(CalculatorResource.class);
				ob = resource.sum( extractQueryParameter(req,"a"), 
									  extractQueryParameter(req,"b"));
				logger.info("/calc/sum/ called");
			}catch(IllegalArgumentException e){
				ob = Observable.empty();
				resp.setStatus(HttpResponseStatus.BAD_REQUEST);
			}catch(Exception e){
				ob = Observable.empty();
				resp.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
			}
			return ob;
		}  
		
		if (req.getPath().startsWith("/calc/sub") && HttpMethod.GET.equals(req.getHttpMethod()) ){
			try{
				CalculatorResource resource = injector.getInstance(CalculatorResource.class);
				ob = resource.sub( extractQueryParameter(req,"a"), 
									  extractQueryParameter(req,"b"));
				logger.info("/calc/sub/ called");
			}catch(IllegalArgumentException e){
				ob = Observable.empty();
				resp.setStatus(HttpResponseStatus.BAD_REQUEST);
			}catch(Exception e){
				ob = Observable.empty();
				resp.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
			}
			return ob;
		}
		
		if (req.getPath().startsWith("/calc/mul") && HttpMethod.GET.equals(req.getHttpMethod()) ){
			try{
				CalculatorResource resource = injector.getInstance(CalculatorResource.class);
				ob = resource.mul( extractQueryParameter(req,"a"), 
									  extractQueryParameter(req,"b"));
				logger.info("/calc/mul/ called");
			}catch(IllegalArgumentException e){
				ob = Observable.empty();
				resp.setStatus(HttpResponseStatus.BAD_REQUEST);
			}catch(Exception e){
				ob = Observable.empty();
				resp.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
			}
			return ob;
		}
		
		if (req.getPath().startsWith("/calc/div") && HttpMethod.GET.equals(req.getHttpMethod()) ){
			try{
				CalculatorResource resource = injector.getInstance(CalculatorResource.class);
				ob = resource.div( extractQueryParameter(req,"a"), 
									  extractQueryParameter(req,"b"));
				logger.info("/calc/div/ called");
			}catch(IllegalArgumentException e){
				ob = Observable.empty();
				resp.setStatus(HttpResponseStatus.BAD_REQUEST);
			}catch(Exception e){
				ob = Observable.empty();
				resp.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
			}
			return ob;
		}
		
		if (req.getPath().startsWith("/calc/slow") && HttpMethod.GET.equals(req.getHttpMethod()) ){
			try{
				CalculatorResource resource = injector.getInstance(CalculatorResource.class);
				ob = resource.slow( extractQueryParameter(req,"a"), 
									  extractQueryParameter(req,"b"));
				logger.info("/calc/slow/ called");
			}catch(IllegalArgumentException e){
				ob = Observable.empty();
				resp.setStatus(HttpResponseStatus.BAD_REQUEST);
			}catch(Exception e){
				ob = Observable.empty();
				resp.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
			}
			return ob;
		}
		
		if (req.getPath().startsWith("/calc/err") && HttpMethod.GET.equals(req.getHttpMethod()) ){
			try{
				CalculatorResource resource = injector.getInstance(CalculatorResource.class);
				ob = resource.err();
				logger.info("/calc/slow/ called");
			}catch(IllegalArgumentException e){
				ob = Observable.empty();
				resp.setStatus(HttpResponseStatus.BAD_REQUEST);
			}catch(Exception e){
				ob = Observable.empty();
				resp.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
			}
			return ob;
		}
		
		ob = Observable.empty();
		logger.info("Unhandled method called: " + req.getPath());
		return ob;
	}
	
	private Double extractQueryParameter(HttpServerRequest req,String name){
		List<String> result = ((List<String>)req.getQueryParameters().get(name));
		return (result==null) ? null : new Double(result.get(0)); 
	}

	public void setInjector(Injector injector) {
		this.injector = injector;
	}
}
