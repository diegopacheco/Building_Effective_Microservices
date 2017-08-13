package com.packtpub.microservice.proxy.ribbon;

import java.nio.charset.Charset;

import com.netflix.ribbon.ClientOptions;
import com.netflix.ribbon.Ribbon;
import com.netflix.ribbon.RibbonResponse;
import com.netflix.ribbon.http.HttpRequestTemplate;
import com.netflix.ribbon.http.HttpResourceGroup;
import com.packtpub.microservice.client.MeetupsByType;

import io.netty.buffer.ByteBuf;
import rx.Observable;
import rx.functions.Action1;

@SuppressWarnings("unchecked")
public class RibbonMeetupClient extends BaseRibbonTemplate {
	
	private <T> Observable<T> execute(Class<T> t,HttpRequestTemplate<ByteBuf> apiTemplate){
		
		RibbonResponse<ByteBuf> result = apiTemplate.requestBuilder().withHeader("client", "client-microservice").build()
				.withMetadata().execute();
		ByteBuf buf = result.content();
		String json = buf.toString(Charset.forName("UTF-8"));
		
		try {
			return Observable.just(mapper.readValue(json, t));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Observable<MeetupsByType> listByType(String type) {
		HttpResourceGroup httpRG = Ribbon.createHttpResourceGroup("apiGroup", ClientOptions.create()
				.withMaxAutoRetriesNextServer(1).withConfigurationBasedServerList(getServerIP("microservice")));

		HttpRequestTemplate<ByteBuf> apiTemplate = httpRG.newTemplateBuilder("apiCall", ByteBuf.class).withMethod("GET")
				.withUriTemplate("/meetup?type=" + type).withFallbackProvider(new DefaultFallback())
				.withResponseValidator(new SimpleResponseValidator()).build();

		return execute(MeetupsByType.class,apiTemplate);
	}
	
	public Observable<Boolean> createMeetup(String name, String type) {
		HttpResourceGroup httpRG = Ribbon.createHttpResourceGroup("apiGroup", ClientOptions.create()
				.withMaxAutoRetriesNextServer(1).withConfigurationBasedServerList(getServerIP("microservice")));

		HttpRequestTemplate<ByteBuf> apiTemplate = httpRG.newTemplateBuilder("apiCall", ByteBuf.class).withMethod("PUT")
				.withUriTemplate("/meetup?type=" + type + "&name=" + name).withFallbackProvider(new DefaultFallback())
				.withResponseValidator(new SimpleResponseValidator()).build();

		return execute(Boolean.class,apiTemplate);
	}	
	
	public static void main(String[] args) {
		
		RibbonMeetupClient client = new RibbonMeetupClient();
		
		client.createMeetup("Ribbon", "tech").subscribe(new Action1<Boolean>() {
			@Override
			public void call(Boolean t) {
				System.out.println("Meetup created? " + t);
			}
		});
		
		client.listByType("tech").subscribe(new Action1<MeetupsByType>() {
			@Override
			public void call(MeetupsByType t) {
				System.out.println(t);
			}
		});
		
	}
}
