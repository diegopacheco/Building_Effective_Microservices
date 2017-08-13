package com.packtpub.microservice.proxy.ribbon;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.netflix.hystrix.HystrixInvokableInfo;
import com.netflix.ribbon.hystrix.FallbackHandler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rx.Observable;

public class DefaultFallback implements FallbackHandler<ByteBuf> {
	@Override
	public Observable<ByteBuf> getFallback(HystrixInvokableInfo<?> hystrixInfo, Map<String, Object> requestProperties) {
		return Observable.from(new java.util.concurrent.Future<ByteBuf>() {

			ByteBuf buf = Unpooled.wrappedBuffer(("Error on call api " + hystrixInfo + " : " + requestProperties).getBytes());

			public boolean cancel(boolean mayInterruptIfRunning) {
				return false;
			}
			public boolean isCancelled() {
				return false;
			}
			public boolean isDone() {
				return true;
			}
			public ByteBuf get() throws InterruptedException, ExecutionException {
				return buf;
			}
			public ByteBuf get(long timeout, TimeUnit unit)
					throws InterruptedException, ExecutionException, TimeoutException {
				return buf;
			}
		});
	}
}