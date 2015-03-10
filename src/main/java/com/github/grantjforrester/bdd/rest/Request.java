package com.github.grantjforrester.bdd.rest;

import java.net.URI;

public interface Request {

	void setMethod(Method method);
	
	void setUri(URI uri);
	
	void setHeader(String name, String value);
	
	void setContent(byte[] content);
}
