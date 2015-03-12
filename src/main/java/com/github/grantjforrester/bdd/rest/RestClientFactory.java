package com.github.grantjforrester.bdd.rest;

import com.github.grantjforrester.bdd.rest.httpclient.HttpClientRestClient;

public class RestClientFactory {

	private static final RestClient instance = new HttpClientRestClient();
	
	public static RestClient getInstance() {
		return instance;
	}
}
