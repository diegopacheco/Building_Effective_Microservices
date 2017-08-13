package com.packtpub.microservice.proxy.ribbon;

import java.nio.charset.Charset;

import com.netflix.ribbon.ClientOptions;
import com.netflix.ribbon.Ribbon;
import com.netflix.ribbon.RibbonResponse;
import com.netflix.ribbon.http.HttpRequestTemplate;
import com.netflix.ribbon.http.HttpResourceGroup;

import io.netty.buffer.ByteBuf;
import rx.Observable;

@SuppressWarnings("unchecked")
public class RibbonMeetupClient extends BaseRibbonTemplate {

	private String serviceListByType(String type) {

		HttpResourceGroup httpRG = Ribbon.createHttpResourceGroup("apiGroup", ClientOptions.create()
				.withMaxAutoRetriesNextServer(1).withConfigurationBasedServerList(getServerIP("microservice")));

		HttpRequestTemplate<ByteBuf> apiTemplate = httpRG.newTemplateBuilder("apiCall", ByteBuf.class).withMethod("GET")
				.withUriTemplate("/meetup?type=" + type).withFallbackProvider(new DefaultFallback())
				.withResponseValidator(new SimpleResponseValidator()).build();

		RibbonResponse<ByteBuf> result = apiTemplate.requestBuilder().withHeader("client", "calc-microservice").build()
				.withMetadata().execute();
		ByteBuf buf = result.content();
		String json = buf.toString(Charset.forName("UTF-8"));
		return json;
	}

	public Observable<String> listByType(String type) {
		return Observable.just(serviceListByType(type));
	}

}
