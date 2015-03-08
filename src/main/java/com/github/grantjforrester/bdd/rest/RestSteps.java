package com.github.grantjforrester.bdd.rest;

import static com.github.grantjforrester.bdd.rest.matchers.ByteArrayEqualityMatcher.matches;
import static com.github.grantjforrester.bdd.rest.util.FileUtils.getFileContent;

import java.io.File;
import java.net.URI;

import com.github.grantjforrester.bdd.rest.httpclient.HttpClientRequest;
import com.github.grantjforrester.bdd.rest.util.FileUtils;


public class RestSteps {

	private URI baseUri = URI.create("/");
	private Request request = new HttpClientRequest();
	private Response response;
	
	public void aServiceRunningOn(URI baseUri) {
		this.baseUri = baseUri;
	}
	
	public void aRequestToTheResource(Method method, URI uri) {
		request.setMethod(method);
		request.setUri(baseUri.resolve(uri));
	}
	
	public void theRequestHasAHeaderWithValue(String name, String value) {
		request.setHeader(name, value);
	}

	public void theRequestHasContent(byte[] content) {
		request.setContent(content);
	}

	public void theRequestHasContentFromFile(File file) {
		request.setContent(FileUtils.getFileContent(file));
	}

	public void theResponseIsReceived() {
		response = request.getResponse();
	}
	
	public void theResponseWillHaveTheStatusCode(int statusCode) {
		if (response.getStatusCode() != statusCode) {
			throw new AssertionError(String.format("Expected status code %d but was %d", statusCode, response.getStatusCode()));
		}
	}

	public void theResponseWillHaveAHeaderWithValue(String name, String value) {
		if (!response.getHeaderValues(name).contains(value)) {
			throw new AssertionError(String.format("No header '%s' with value '%s' found", name, value));
		}
	}

	public void theResponseWillNotHaveAHeaderWithValue(String name, String value) {
		if (response.getHeaderValues(name).contains(value)) {
			throw new AssertionError(String.format("Found forbidden header '%s' with value '%s'", name, value));
		}
	}

	public void theResponseContentWillMatch(byte[] content) {
		if (!matches(content, response.getContent())) {
			throw new AssertionError("Actual content does not match expected content");
		}
	}

	public void theResponseContentWillMatchTheFile(File file) {
		theResponseContentWillMatch(getFileContent(file));
	}
}
