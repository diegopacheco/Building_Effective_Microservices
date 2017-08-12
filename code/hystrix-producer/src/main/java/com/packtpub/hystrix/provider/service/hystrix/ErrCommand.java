package com.packtpub.hystrix.provider.service.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observable;
import rx.Subscriber;

public class ErrCommand extends HystrixObservableCommand<Double> {
	
    public ErrCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("HystrixGroup"));
    }

    @Override
    protected Observable<Double> construct() {
        return Observable.create(new Observable.OnSubscribe<Double>() {
            @Override
            public void call(Subscriber<? super Double> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                    	throw new RuntimeException("Error by Design!");
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
         } );
    }
}