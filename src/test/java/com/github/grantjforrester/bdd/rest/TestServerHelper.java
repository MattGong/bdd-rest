package com.github.grantjforrester.bdd.rest;

import static com.github.dreamhead.moco.Moco.httpserver;
import static java.lang.Integer.parseInt;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runner;

class TestServerHelper {

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
	
	String baseUri() {
		try {
			URI baseUri = new URIBuilder().setScheme(TEST_PROTOCOL).setHost(TEST_HOST).setPort(testPort).build();
			return baseUri.toString();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
	
	HttpServer expect() {
		return server;
	}

	void start() {
		runner.start();
	}

	void stop() {
		runner.stop();
	}

}