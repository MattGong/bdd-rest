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

import static com.github.dreamhead.moco.Moco.httpserver;
import static java.lang.Integer.parseInt;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;

public class TestServerHelper {

	private static final String TEST_PROTOCOL = "http";
	private static final String TEST_HOST = "localhost";
	private static final String DEFAULT_TEST_PORT = "8080";
	private static final String TEST_PORT_PROPERTY = "test.server.port";

	private final int testPort = parseInt(System.getProperty(TEST_PORT_PROPERTY, DEFAULT_TEST_PORT));
	private final HttpServer server;
	private final Runner runner;
	
	public TestServerHelper() {
		server = httpserver(testPort);
		runner = Runner.runner(server);
	}
	
	public String baseUri() {
		try {
			URI baseUri = new URIBuilder().setScheme(TEST_PROTOCOL).setHost(TEST_HOST).setPort(testPort).build();
			return baseUri.toString();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
	
	public HttpServer expect() {
		return server;
	}

	public void start() {
		runner.start();
	}

	public void stop() {
		runner.stop();
	}

}