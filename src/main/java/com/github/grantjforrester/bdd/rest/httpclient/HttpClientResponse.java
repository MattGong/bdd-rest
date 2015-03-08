package com.github.grantjforrester.bdd.rest.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.github.grantjforrester.bdd.rest.Response;

public class HttpClientResponse implements Response {

	private final HttpResponse httpResponse;
	
	HttpClientResponse(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	public int getStatusCode() {
		return httpResponse.getStatusLine().getStatusCode();
	}

	public Collection<String> getHeaderValues(String headerName) {
		List<String> results = new ArrayList<String>();
		
		for (Header header : httpResponse.getHeaders(headerName)) {
			results.add(header.getValue());
		}
		
		return results;
	}

	public byte[] getContent() {
		try {
			return EntityUtils.toByteArray(httpResponse.getEntity());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
