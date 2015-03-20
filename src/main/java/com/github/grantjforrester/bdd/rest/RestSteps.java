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

/**
 * Provides the implementation of the bdd-rest steps to which language specific sub-class bind.
 */
public class RestSteps {

	private final RestClient client = RestClientFactory.getInstance();
		
	/**
	 * Configures the base URI of the endpoint.
	 * @param baseUri
	 */
	public void aServiceRunningOn(String baseUri) {
		client.setBaseUri(URI.create(baseUri));
	}
	
	/**
	 * Sets the method a resoure URI of the request.
	 * @param method
	 * @param uri
	 */
	public void aRequestToTheResource(Method method, String uri) {
		client.getRequest().setMethod(method);
		client.getRequest().setUri(URI.create(uri));
	}
	
	/**
	 * Sets a header on the request with the given name and value.
	 * @param name
	 * @param value
	 */
	public void theRequestHasAHeaderWithValue(String name, String value) {
		client.getRequest().setHeader(name, value);
	}

	/**
	 * Sets the content of the request from the given string.
	 * @param content
	 */
	public void theRequestHasContent(String content) {
		client.getRequest().setContent(content.getBytes());
	}

	/**
	 * Sets the content of the request from the contents of the named file.
	 * @param filename
	 */
	public void theRequestHasContentFromFile(String filename) {
		client.getRequest().setContent(FileUtils.getFileContent(new File(filename)));
	}

	/**
	 * Executes the request and receives the response.
	 */
	public void theResponseIsReceived() {
		client.executeRequest();
	}
	
	/**
	 * Verifies the status code of the response.
	 * @param statusCode
	 */
	public void theResponseWillHaveTheStatusCode(int statusCode) {
		if (client.getResponse().getStatusCode() != statusCode) {
			throw new AssertionError(String.format("Expected status code %d but was %d", statusCode, client.getResponse().getStatusCode()));
		}
	}

	/**
	 * Verifies the presence of a response header with the given name and value.
	 * @param name
	 * @param value
	 */
	public void theResponseWillHaveAHeaderWithValue(String name, String value) {
		if (!client.getResponse().getHeaderValues(name).contains(value)) {
			throw new AssertionError(String.format("No header '%s' with value '%s' found", name, value));
		}
	}

	/**
	 * Verifies the absence of a response header with the given name and value.
	 * @param name
	 * @param value
	 */
	public void theResponseWillNotHaveAHeaderWithValue(String name, String value) {
		if (client.getResponse().getHeaderValues(name).contains(value)) {
			throw new AssertionError(String.format("Found forbidden header '%s' with value '%s'", name, value));
		}
	}

	/**
	 * Verifies the response content matches the given string.
	 * @param content
	 */
	public void theResponseContentWillMatch(String content) {
		if (!matches(content.getBytes(), client.getResponse().getContent())) {
			throw new AssertionError("Actual content does not match expected content");
		}
	}

	/**
	 * Verifies the response content matches the contents of the file with the given name.
	 * @param filename
	 */
	public void theResponseContentWillMatchTheFile(String filename) {
		if (!matches(getFileContent(new File(filename)), client.getResponse().getContent())) {
			throw new AssertionError("Actual content does not match expected content");
		}
	}
	
	/**
	 * Resets any previous request and response.
	 */
	public void resetRestClient() {
		client.reset();
	}
}
