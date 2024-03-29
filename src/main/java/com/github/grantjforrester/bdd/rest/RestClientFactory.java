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

import com.github.grantjforrester.bdd.rest.httpclient.HttpClientRestClient;

/**
 * A <code>RestClient</code> factory that maintains a singleton of a <code>RestClient</code>.
 * The <code>RestClient</code> is eagerly instantiated when the class is loaded.
 */
public class RestClientFactory {

	private static final RestClient instance = new HttpClientRestClient();
	
	/**
	 * Returns the <code>RestClient</code> singleton.
	 * @return the client
	 */
	public static RestClient getInstance() {
		return instance;
	}
}
