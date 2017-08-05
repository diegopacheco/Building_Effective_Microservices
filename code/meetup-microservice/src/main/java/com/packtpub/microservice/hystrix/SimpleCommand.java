package com.packtpub.microservice.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observable;
import rx.Subscriber;

public class SimpleCommand extends HystrixObservableCommand<String> {

	private final String ops;

	public SimpleCommand(String ops) {
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.ops = ops;
	}

	@Override
	protected Observable<String> construct() {
		return Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> observer) {
				try {
					if (!observer.isUnsubscribed()) {
						observer.onNext("{ \"operations\": " + ops + "}");
						observer.onCompleted();
					}
				} catch (Exception e) {
					observer.onError(e);
				}
			}
		});
	}
}
