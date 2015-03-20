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

import java.net.URI;

/**
 * The HTTP client that takes the sends the request and receives the response. 
 */
public interface RestClient {

	/**
	 * Set the base URI of the request.
	 * @param baseUri
	 */
	void setBaseUri(URI baseUri);
	
	/**
	 * Returns the current request.
	 * @return the request.
	 */
	Request getRequest();
	
	/**
	 * Executes the current request and receives the response.
	 */
	void executeRequest();
	
	/**
	 * Returns the current response.
	 * @return the response, or null.
	 */
	Response getResponse();
	
	/**
	 * Resets the current request and removes the current response freeing up any resources.
	 */
	void reset();
}
