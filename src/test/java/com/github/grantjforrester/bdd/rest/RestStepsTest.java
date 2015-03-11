package com.github.grantjforrester.bdd.rest;

import static com.github.dreamhead.moco.Moco.and;
import static com.github.dreamhead.moco.Moco.by;
import static com.github.dreamhead.moco.Moco.eq;
import static com.github.dreamhead.moco.Moco.header;
import static com.github.dreamhead.moco.Moco.method;
import static com.github.dreamhead.moco.Moco.status;
import static com.github.dreamhead.moco.Moco.text;
import static com.github.dreamhead.moco.Moco.uri;
import static com.github.dreamhead.moco.Moco.with;
import static com.github.grantjforrester.bdd.rest.Method.DELETE;
import static com.github.grantjforrester.bdd.rest.Method.GET;
import static com.github.grantjforrester.bdd.rest.Method.HEAD;
import static com.github.grantjforrester.bdd.rest.Method.OPTIONS;
import static com.github.grantjforrester.bdd.rest.Method.PATCH;
import static com.github.grantjforrester.bdd.rest.Method.POST;
import static com.github.grantjforrester.bdd.rest.Method.PUT;
import static com.googlecode.catchexception.throwable.CatchThrowable.catchThrowable;
import static com.googlecode.catchexception.throwable.CatchThrowable.caughtThrowable;
import static com.googlecode.catchexception.throwable.apis.CatchThrowableHamcrestMatchers.hasMessage;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RestStepsTest {

	private static final int OK = 200;
	private final String uri = "/someResource";
	private final String representation = "someRepresentation";
	private final String filename = "src/test/resources/com/github/grantjforrester/bdd/rest/resource.txt";
	private final String header = "someHeader";
	private final String value = "someValue";
	private RestSteps testee;
	private TestServerHelper testServer;
	
	@Before
	public void setUp() throws Exception {
		testee = new RestSteps();
		testServer = new TestServerHelper();
		testServer.start();
	}
	
	@After
	public void tearDown() throws Exception {
		testServer.stop();
		testee.resetRestClient();
	}
	
	@Test
	public void shouldSendHeadRequestToUrl() throws Exception {
		testServer.expect().request(and(by(method("HEAD")), by(uri(uri)))).response(status(200));
		
		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(HEAD, uri);
		testee.theResponseIsReceived();
		testee.theResponseWillHaveTheStatusCode(OK);
	}
	
	@Test
	public void shouldSendOptionsRequestToUrl() throws Exception {
		testServer.expect().request(and(by(method("OPTIONS")), by(uri(uri)))).response(status(200));
	
		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(OPTIONS, uri);
		testee.theResponseIsReceived();
		testee.theResponseWillHaveTheStatusCode(OK);
	}

	@Test
	public void shouldSendGetRequestToUrl() throws Exception {
		testServer.expect().get(by(uri(uri))).response(representation);
		
		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(GET, uri);
		testee.theResponseIsReceived();
		testee.theResponseWillHaveTheStatusCode(OK);
	}

	@Test
	public void shouldSendGetRequestWithHeaderToUrl() throws Exception {
		testServer.expect().get(and(by(uri(uri)), eq(header(header), value))).response(representation);
		
		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(GET, uri);
		testee.theRequestHasAHeaderWithValue(header, value);
		testee.theResponseIsReceived();
		testee.theResponseWillHaveTheStatusCode(OK);
	}
	
	@Test
	public void shouldSendPostRequestWithContentToUrl() throws Exception {
		testServer.expect().post(and(by(uri(uri)), by(representation))).response(status(OK));
		
		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(POST, uri);
		testee.theRequestHasContent(representation);
		testee.theResponseIsReceived();
		testee.theResponseWillHaveTheStatusCode(OK);
	}
	
	@Test
	public void shouldSendPostRequestWithContentFromFileToUrl() throws Exception {
		testServer.expect().post(and(by(uri(uri)), by(representation))).response(status(OK));
		
		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(POST, uri);
		testee.theRequestHasContentFromFile(filename);
		testee.theResponseIsReceived();
		testee.theResponseWillHaveTheStatusCode(OK);
	}

	
	@Test
	public void shouldSendPutRequestWithContentToUrl() throws Exception {
		testServer.expect().put(and(by(uri(uri)), by(representation))).response(status(OK));

		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(PUT, uri);
		testee.theRequestHasContent(representation);
		testee.theResponseIsReceived();
		testee.theResponseWillHaveTheStatusCode(OK);
	}
	
	@Test
	public void shouldSendDeleteRequestToUrl() throws Exception {
		testServer.expect().delete(by(uri(uri))).response(status(OK));

		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(DELETE, uri);
		testee.theResponseIsReceived();
		testee.theResponseWillHaveTheStatusCode(OK);
	}
	
	@Test
	public void shouldSendPatchRequestToUrl() throws Exception {
		testServer.expect().request(and(by(method("PATCH")), by(uri(uri)))).response(status(OK));

		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(PATCH, uri);
		testee.theRequestHasContent(representation);
		testee.theResponseIsReceived();
		testee.theResponseWillHaveTheStatusCode(OK);
	}
	
	@Test 
	public void shouldFailWhenResponseHasIncorrectStatusCode() throws Exception {
		testServer.expect().get(by(uri(uri))).response(status(418));
		
		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(GET, uri);
		testee.theResponseIsReceived();

		catchThrowable(testee).theResponseWillHaveTheStatusCode(OK);
		assertThat(caughtThrowable(), instanceOf(AssertionError.class));
		assertThat(caughtThrowable(), hasMessage("Expected status code " + OK + " but was 418"));
	}
	
	@Test 
	public void shouldPassWhenResponseHasExpectedHeader() throws Exception {
		testServer.expect().get(by(uri(uri))).response(with(text(representation)), header(header, value));
		
		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(GET, uri);
		testee.theResponseIsReceived();
		testee.theResponseWillHaveTheStatusCode(OK);
		testee.theResponseWillHaveAHeaderWithValue(header, value);
	}
	
	@Test 
	public void shouldFailWhenResponseMissingExpectedHeader() throws Exception {
		testServer.expect().get(by(uri(uri))).response(representation);
		
		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(GET, uri);
		testee.theResponseIsReceived();
		testee.theResponseWillHaveTheStatusCode(OK);

		catchThrowable(testee).theResponseWillHaveAHeaderWithValue(header, value);
		assertThat(caughtThrowable(), instanceOf(AssertionError.class));
		assertThat(caughtThrowable(), hasMessage("No header '" + header + "' with value '" + value + "' found"));
	}
	
	@Test 
	public void shouldPassWhenResponseDoesNotHaveBannedHeader() throws Exception {
		testServer.expect().get(by(uri(uri))).response(representation);
		
		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(GET, uri);
		testee.theResponseIsReceived();
		testee.theResponseWillHaveTheStatusCode(OK);
		testee.theResponseWillNotHaveAHeaderWithValue(header, value);
	}
	
	@Test 
	public void shouldFailWhenResponseHasForbiddenHeader() throws Exception {
		testServer.expect().get(by(uri(uri))).response(with(text(representation)), header(header, value));
		
		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(GET, uri);
		testee.theResponseIsReceived();
		testee.theResponseWillHaveTheStatusCode(OK);

		catchThrowable(testee).theResponseWillNotHaveAHeaderWithValue(header, value);
		assertThat(caughtThrowable(), instanceOf(AssertionError.class));
		assertThat(caughtThrowable(), hasMessage("Found forbidden header '" + header + "' with value '" + value + "'"));
	}

	@Test 
	public void shouldPassWhenContentMatches() throws Exception {
		testServer.expect().get(by(uri(uri))).response(representation);
		
		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(GET, uri);
		testee.theResponseIsReceived();
		testee.theResponseWillHaveTheStatusCode(OK);
		testee.theResponseContentWillMatch(representation);
	}
	
	@Test 
	public void shouldFailWhenContentDoesNotMatch() throws Exception {
		testServer.expect().get(by(uri(uri))).response("wrongRepresentation");
		
		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(GET, uri);
		testee.theResponseIsReceived();
		testee.theResponseWillHaveTheStatusCode(OK);

		catchThrowable(testee).theResponseContentWillMatch(representation);
		assertThat(caughtThrowable(), instanceOf(AssertionError.class));
		assertThat(caughtThrowable(), hasMessage("Actual content does not match expected content"));
	}

	@Test 
	public void shouldPassWhenContentFromFileMatches() throws Exception {
		testServer.expect().get(by(uri(uri))).response(representation);
		
		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(GET, uri);
		testee.theResponseIsReceived();
		testee.theResponseWillHaveTheStatusCode(OK);
		testee.theResponseContentWillMatchTheFile(filename);
	}
	
	@Test 
	public void shouldFailWhenContentFromFileDoesNotMatch() throws Exception {
		testServer.expect().get(by(uri(uri))).response("wrongRepresentation");
		
		testee.aServiceRunningOn(testServer.baseUri());
		testee.aRequestToTheResource(GET, uri);
		testee.theResponseIsReceived();
		testee.theResponseWillHaveTheStatusCode(OK);

		catchThrowable(testee).theResponseContentWillMatch(representation);
		assertThat(caughtThrowable(), instanceOf(AssertionError.class));
		assertThat(caughtThrowable(), hasMessage("Actual content does not match expected content"));
	}
}
