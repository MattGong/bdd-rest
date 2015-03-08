package com.github.grantjforrester.bdd.rest;

import java.util.Collection;

public interface Response {

	int getStatusCode();
	
	Collection<String> getHeaderValues(String headerName);
	
	byte[] getContent();
}
