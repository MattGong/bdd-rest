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
package com.github.grantjforrester.bdd.rest;

import static com.github.grantjforrester.bdd.rest.matchers.ByteArrayEqualityMatcher.matches;
import static com.github.grantjforrester.bdd.rest.util.FileUtils.getFileContent;

import java.io.File;
import java.net.URI;

import com.github.grantjforrester.bdd.rest.util.FileUtils;


public class RestSteps {

	private final RestClient client = RestClientFactory.getInstance();
		
	public void aServiceRunningOn(String baseUri) {
		client.setBaseUri(URI.create(baseUri));
	}
	
	public void aRequestToTheResource(Method method, String uri) {
		client.getRequest().setMethod(method);
		client.getRequest().setUri(URI.create(uri));
	}
	
	public void theRequestHasAHeaderWithValue(String name, String value) {
		client.getRequest().setHeader(name, value);
	}

	public void theRequestHasContent(String content) {
		client.getRequest().setContent(content.getBytes());
	}

	public void theRequestHasContentFromFile(String filename) {
		client.getRequest().setContent(FileUtils.getFileContent(new File(filename)));
	}

	public void theResponseIsReceived() {
		client.executeRequest();
	}
	
	public void theResponseWillHaveTheStatusCode(int statusCode) {
		if (client.getResponse().getStatusCode() != statusCode) {
			throw new AssertionError(String.format("Expected status code %d but was %d", statusCode, client.getResponse().getStatusCode()));
		}
	}

	public void theResponseWillHaveAHeaderWithValue(String name, String value) {
		if (!client.getResponse().getHeaderValues(name).contains(value)) {
			throw new AssertionError(String.format("No header '%s' with value '%s' found", name, value));
		}
	}

	public void theResponseWillNotHaveAHeaderWithValue(String name, String value) {
		if (client.getResponse().getHeaderValues(name).contains(value)) {
			throw new AssertionError(String.format("Found forbidden header '%s' with value '%s'", name, value));
		}
	}

	public void theResponseContentWillMatch(String content) {
		if (!matches(content.getBytes(), client.getResponse().getContent())) {
			throw new AssertionError("Actual content does not match expected content");
		}
	}

	public void theResponseContentWillMatchTheFile(String filename) {
		if (!matches(getFileContent(new File(filename)), client.getResponse().getContent())) {
			throw new AssertionError("Actual content does not match expected content");
		}
	}
	
	public void resetRestClient() {
		client.reset();
	}
}
