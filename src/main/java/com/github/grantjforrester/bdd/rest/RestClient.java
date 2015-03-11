package com.github.grantjforrester.bdd.rest;

import java.net.URI;

public interface RestClient {

	void setBaseUri(URI baseUri);
	
	Request getRequest();
	
	void executeRequest();
	
	Response getResponse();
	
	void reset();
}
