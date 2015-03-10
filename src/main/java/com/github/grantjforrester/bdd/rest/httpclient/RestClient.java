package com.github.grantjforrester.bdd.rest.httpclient;

import java.io.IOException;
import java.net.URI;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.github.grantjforrester.bdd.rest.Method;
import com.github.grantjforrester.bdd.rest.Request;
import com.github.grantjforrester.bdd.rest.Response;

public class RestClient {
	private static final RestClient instance = new RestClient();
	private CloseableHttpClient client = HttpClients.createDefault();
	private URI baseUri = URI.create("/");
	private HttpClientRequest request;
	private HttpClientResponse response;
	
	public static RestClient getInstance() {
		return instance;
	}
	
	public void setBaseUri(URI baseUri) {
		this.baseUri = baseUri;
	}
	
	public void newRequest(Method method, URI uri) {
		request = new HttpClientRequest();
		request.setMethod(method);
		request.setUri(baseUri.resolve(uri));
		closeResponse();
	}
	
	public Request getRequest() {
		return request;
	}

	public void executeRequest() {
		try {
			if (response == null) {
				response = new HttpClientResponse(client.execute(request.getRequestImpl()));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Response getResponse() {
		return response;
	}
	
	private void closeResponse() {
		if (response != null) {
			response.close();
			response = null;
		}	
	}
}
