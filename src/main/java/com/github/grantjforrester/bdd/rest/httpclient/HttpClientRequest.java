package com.github.grantjforrester.bdd.rest.httpclient;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.github.grantjforrester.bdd.rest.Method;
import com.github.grantjforrester.bdd.rest.Request;
import com.github.grantjforrester.bdd.rest.Response;

public class HttpClientRequest implements Request {
 
	private HttpClient client = HttpClientBuilder.create().build();
	private HttpRequestBase request;
	private Response response;
	
	public void setMethod(Method method) {
		switch(method) {
			case HEAD: 		request = new HttpHead();
							break;
			case OPTIONS: 	request = new HttpOptions();
							break;
			case GET: 		request = new HttpGet();
							break;
			case POST: 		request = new HttpPost();
							break;
			case PUT:  		request = new HttpPut();
							break;
			case DELETE:	request = new HttpDelete();
							break;
			case PATCH:		request = new HttpPatch();
		}
	}

	public void setUri(URI uri) {
		request.setURI(uri);
	}

	public void setHeader(String name, String value) {
		request.addHeader(name, value);
	}
	
	public void setContent(byte[] content) {
		((HttpEntityEnclosingRequest)request).setEntity(new ByteArrayEntity(content));
	}

	public Response getResponse() {
		try {
			if (response == null) {
				response = new HttpClientResponse(client.execute(request));
			}
			return response;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
