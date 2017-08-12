package com.packtpub.hystrix.provider.service.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observable;
import rx.Subscriber;

public class SlowCommand extends HystrixObservableCommand<Double> {
	
    private final Double a;
    private final Double b;

    public SlowCommand(Double a,Double b) {
        super(HystrixCommandGroupKey.Factory.asKey("HystrixGroup"));
        this.a = a;
        this.b = b;
    }

    @Override
    protected Observable<Double> construct() {
        return Observable.create(new Observable.OnSubscribe<Double>() {
            @Override
            public void call(Subscriber<? super Double> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                    	Thread.sleep(3000);
                        observer.onNext((a+b));
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
         } );
    }
}