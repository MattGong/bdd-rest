package com.github.grantjforrester.bdd.rest.jbehave;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.github.grantjforrester.bdd.rest.Method;
import com.github.grantjforrester.bdd.rest.RestSteps;

public class JBehaveRestSteps extends RestSteps {

	@Override
	@Given("a service running on $baseUri")
	public void aServiceRunningOn(String baseUri) {
		super.aServiceRunningOn(baseUri);
	}
	
	@Override
	@Given("a $method request to{ a| the|} resource $uri")
	public void aRequestToTheResource(Method method, String uri) {
		super.aRequestToTheResource(method, uri);
	}
	
	@Override
	@Given("the request has{ a| the|} header '$name' with{ a| the|} value '$value'")
	public void theRequestHasAHeaderWithValue(String name, String value) {
		super.theRequestHasAHeaderWithValue(name, value);
	}
	
	@Override
	@Given("the request has{ the|} content '$content'")
	public void theRequestHasContent(String content) {
		super.theRequestHasContent(content);
	}
	
	@Override
	@Given("the request has content from{ a| the|} file '$file'")
	public void theRequestHasContentFromFile(String filename) {
		super.theRequestHasContentFromFile(filename);
	}
	
	@Override
	@When("the response is received")
	public void theResponseIsReceived() {
		super.theResponseIsReceived();
	}
	
	@Override
	@Then("the response will have{ a| the|} status code $statusCode")
	public void theResponseWillHaveTheStatusCode(int statusCode) {
		super.theResponseWillHaveTheStatusCode(statusCode);
	}
	
	@Override
	@Then("the response will have{ a| the|} header '$name' with{ a| the|} value '$value'")
	public void theResponseWillHaveAHeaderWithValue(String name, String value) {
		super.theResponseWillHaveAHeaderWithValue(name, value);
	}
	
	@Override
	@Then("the response will not have{ a| the|} header '$name' with{ a| the|} value '$value'")
	public void theResponseWillNotHaveAHeaderWithValue(String name, String value) {
		super.theResponseWillNotHaveAHeaderWithValue(name, value);
	}
	
	@Override
	@Then("the response content will match '$content'")
	public void theResponseContentWillMatch(String content) {
		super.theResponseContentWillMatch(content);
	}
	
	@Override
	@Then("the response content will match{ a| the} file '$file'")
	public void theResponseContentWillMatchTheFile(String filename) {
		super.theResponseContentWillMatchTheFile(filename);
	}
	
	@Override
	@AfterScenario
	public void resetRestClient() {
		super.resetRestClient();
	}
}
