package com.github.grantjforrester.bdd.rest.httpclient;

import java.io.IOException;
import java.net.URI;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.github.grantjforrester.bdd.rest.Request;
import com.github.grantjforrester.bdd.rest.Response;
import com.github.grantjforrester.bdd.rest.RestClient;

public class HttpClientRestClient implements RestClient {
	private static final HttpClientRestClient instance = new HttpClientRestClient();
	private CloseableHttpClient client = HttpClients.createDefault();
	private URI baseUri = URI.create("/");
	private HttpClientRequest request = new HttpClientRequest();
	private HttpClientResponse response;
	
	public static HttpClientRestClient getInstance() {
		return instance;
	}
	
	public void setBaseUri(URI baseUri) {
		this.baseUri = baseUri;
	}
	
	public Request getRequest() {
		return request;
	}

	public void executeRequest() {
		try {
			response = new HttpClientResponse(client.execute(request.getRequestImpl(baseUri)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Response getResponse() {
		return response;
	}
	
	public void reset() {
		request = new HttpClientRequest();
		if (response != null) {
			response.close();
			response = null;
		}	
	}
}
