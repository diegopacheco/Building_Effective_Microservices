package com.packtpub.hystrix.provider.service;

import rx.Observable;

public interface CalculatorService {

	public Observable<Double> sum(Double a, Double b);

	public Observable<Double> sub(Double a, Double b);

	public Observable<Double> mul(Double a, Double b);

	public Observable<Double> div(Double a, Double b);
	
	public Observable<Double> slow(Double a, Double b);
	
	public Observable<Double> err();

}
