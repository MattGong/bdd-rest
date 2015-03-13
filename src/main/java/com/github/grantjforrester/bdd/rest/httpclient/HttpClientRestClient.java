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
import java.net.URI;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.github.grantjforrester.bdd.rest.Request;
import com.github.grantjforrester.bdd.rest.Response;
import com.github.grantjforrester.bdd.rest.RestClient;

public class HttpClientRestClient implements RestClient {

	private CloseableHttpClient client = HttpClients.createDefault();
	private URI baseUri = URI.create("/");
	private HttpClientRequest request = new HttpClientRequest();
	private HttpClientResponse response;

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
