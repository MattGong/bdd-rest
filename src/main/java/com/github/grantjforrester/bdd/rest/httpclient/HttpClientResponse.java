/**
 * Copyright 2015 Grant Forrester
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.grantjforrester.bdd.rest.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import com.github.grantjforrester.bdd.rest.Response;

class HttpClientResponse implements Response {

	private final CloseableHttpResponse httpResponse;
	
	HttpClientResponse(CloseableHttpResponse httpResponse) {
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
	
	void close() {
		try {
			httpResponse.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
