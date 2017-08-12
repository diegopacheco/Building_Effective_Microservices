package com.packtpub.hystrix.provider.service;

import com.packtpub.hystrix.provider.service.hystrix.DivCommand;
import com.packtpub.hystrix.provider.service.hystrix.MulCommand;
import com.packtpub.hystrix.provider.service.hystrix.SubCommand;
import com.packtpub.hystrix.provider.service.hystrix.SumCommand;

import rx.Observable;

public class CalculatorServiceImpl implements CalculatorService {

	@Override
	public Observable<Double> sum(Double a, Double b){
		validate(a, b);
		return new SumCommand(a, b).toObservable();
	}
	
	@Override
	public Observable<Double> sub(Double a, Double b){
		validate(a, b);
		return new SubCommand(a, b).toObservable();
	}
	
	@Override
	public Observable<Double> mul(Double a, Double b){
		validate(a, b);
		return new MulCommand(a, b).toObservable();
	}
	
	@Override
	public Observable<Double> div(Double a, Double b){
		validate(a, b);
		return new DivCommand(a, b).toObservable();
	}
	
	private void validate(Double a, Double b) {
		if(null==a) 
			throw new IllegalArgumentException("You must supply values for A");
		if(null==b) 
			throw new IllegalArgumentException("You must supply values for B");
	}
	
}
