# bdd-rest
'bdd-rest' is a JVM library for specifying and testing RESTful APIs with Gherkin.  Using this library with the Cucumber-JVM or JBehave BDD tools and you can specify and test your RESTful API with Gherkin scenarios like this.

```
Feature: Search

Scenario: Search for term 'BDD' 
Given a service running on https://www.google.com
And a GET request to the resource /intl/en/about
And the request has header 'Accept' with value '/*'
When the response is received
Then the response will have the status code 200
And the response will have header 'Content-Type' with value 'text/html'
And the response will not have a header 'foo' with value 'bar' 
```

## Requirements

- Java 1.5+
- [HttpClient](https://hc.apache.org/httpcomponents-client-ga/) 4.3+ (+ its dependencies)

And your choice of [Cucumber-JVM](https://cukes.info/) 1.2 or [JBehave](http://jbehave.org/) 3.9 depending on your preferred BDD tool.

## Installing

TODO: Publish this library so it is available from Maven Central

## Using

### Writing Scenarios

The Gherkin grammar provided by the bdd-rest is described in [the grammar guide](grammar.md).

An example scenario:

```
Feature: About

Scenario: Get Google About page 
Given a service running on https://www.google.com
And a GET request to the resource /intl/en/about
When the response is received
Then the response will have the status code 200
```

### Running Scenarios with Maven and Cucumber

See the [maven-cucumber](examples/maven-cucumber) example for details of how to setup a Maven project using bdd-rest with Cucumber.

### Running Scenarios with Maven and JBehave

See the [maven-jbehave](examples/maven-jbehave) example for details of how to setup a Maven project using bdd-rest with JBehave.


## Extending

It's really easy to extend the grammar to add your own custom steps for setting up your requests or verifying your responses.  Create a new class and use the RestClientFactory to get the current request or response.  Then just make sure you include the package name for your class when you run Cucumber or JBehave.

Java Example
```Java
package com.mypackage.bdd;

import com.github.grantjforrester.bdd.rest.RestClient;
import com.github.grantjforrester.bdd.rest.RestClientFactory;
...

public class MyCustomSteps {

  private final RestClient client = RestClientFactory.getInstance();
  
  @When("the request has something special")
  public void theRequestHasSomethingSpecial() {
    client.getRequest()......
  }
  
  @Then("the response has something special") {
    client.getResponse()......
  }
  ...
}
```

## Limitations

- The first step in any scenario must be `Given a service running on ...`
- The content of your HTTP requests and reponses are loaded into an in-memory byte[] and are therefore limited to ~2GB
- The expected and actual contents are compared as an exact byte[] match are therefore syntactic rather than semantic comparisons.

## Roadmap

Add more flexible content matchers
- Regex matching
- Semantic JSON matching
- Semantic XML matching
- Normalised whitespance HTML matching

## Version History


0.5 Adpoted Apache license, added grammar guide, added Javadoc and required POM sections

0.4 Introduced RestClientFactory, added README

0.3 First Junit runner support

0.2 First JBehave support

0.1 First Cucumber support

## License

Copyright 2015 Grant Forrester

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

