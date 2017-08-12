package com.packtpub.hystrix.provider.rest;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.packtpub.hystrix.provider.service.CalculatorService;

import rx.Observable;

@Singleton
@Path("/calc")
public class CalculatorResource {
	
	private CalculatorService service;
	
	@Inject
	public CalculatorResource(CalculatorService service) {
		this.service = service;
	}
	
	@GET
	@Path("/sum")
	@Produces(MediaType.TEXT_PLAIN)
	public Observable<Double> sum(@QueryParam("a") Double a,@QueryParam("b") Double b) {
		return service.sum(a,b);
	}
	
	@GET
	@Path("/sub")
	@Produces(MediaType.TEXT_PLAIN)
	public Observable<Double> sub(@QueryParam("a") Double a,@QueryParam("b") Double b) {
		return service.sub(a,b);
	}
	
	@GET
	@Path("/mul")
	@Produces(MediaType.TEXT_PLAIN)
	public Observable<Double> mul(@QueryParam("a") Double a,@QueryParam("b") Double b) {
		return service.mul(a,b);
	}
	
	@GET
	@Path("/div")
	@Produces(MediaType.TEXT_PLAIN)
	public Observable<Double> div(@QueryParam("a") Double a,@QueryParam("b") Double b) {
		return service.div(a,b);
	}
	
	@GET
	@Path("/slow")
	@Produces(MediaType.TEXT_PLAIN)
	public Observable<Double> slow(@QueryParam("a") Double a,@QueryParam("b") Double b) {
		return service.slow(a,b);
	}
	
	@GET
	@Path("/err")
	@Produces(MediaType.TEXT_PLAIN)
	public Observable<Double> err() {
		return service.err();
	}
	
}
