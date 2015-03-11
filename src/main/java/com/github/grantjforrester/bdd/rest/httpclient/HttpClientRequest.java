package com.github.grantjforrester.bdd.rest.httpclient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
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
import org.apache.http.message.BasicHeader;

import com.github.grantjforrester.bdd.rest.Method;
import com.github.grantjforrester.bdd.rest.Request;

public class HttpClientRequest implements Request {
 
	private Method method;
	private URI uri;
	private List<Header> headers = new ArrayList<Header>();
	private byte[] content;
	
	public void setMethod(Method method) {
		this.method = method;
	}
 
	public void setUri(URI uri) {
		this.uri = uri;
	}

	public void setHeader(String name, String value) {
		headers.add(new BasicHeader(name, value));
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	HttpRequestBase getRequestImpl(URI baseUri) {
		HttpRequestBase request = null;
		
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

		request.setURI(baseUri.resolve(uri));
		request.setHeaders(headers.toArray(new Header[headers.size()]));
		if (content != null) {
			((HttpEntityEnclosingRequest)request).setEntity(new ByteArrayEntity(content));
		}
		
		return request;
	}
}
