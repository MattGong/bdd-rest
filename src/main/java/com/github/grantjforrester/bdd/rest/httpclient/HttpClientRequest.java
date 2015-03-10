package com.github.grantjforrester.bdd.rest.httpclient;

import java.net.URI;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;

import com.github.grantjforrester.bdd.rest.Method;
import com.github.grantjforrester.bdd.rest.Request;

public class HttpClientRequest implements Request {
 
	private HttpRequestBase request;
	
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
	
	HttpRequestBase getRequestImpl() {
		return request;
	}
}
