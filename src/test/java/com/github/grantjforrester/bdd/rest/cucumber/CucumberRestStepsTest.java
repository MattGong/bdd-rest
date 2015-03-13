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
package com.github.grantjforrester.bdd.rest.cucumber;

import static com.github.dreamhead.moco.Moco.and;
import static com.github.dreamhead.moco.Moco.by;
import static com.github.dreamhead.moco.Moco.eq;
import static com.github.dreamhead.moco.Moco.header;
import static com.github.dreamhead.moco.Moco.status;
import static com.github.dreamhead.moco.Moco.text;
import static com.github.dreamhead.moco.Moco.uri;
import static com.github.dreamhead.moco.Moco.with;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.github.grantjforrester.bdd.rest.TestServerHelper;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(glue="com.github.grantjforrester.bdd.rest.cucumber", 
		features="src/test/resources/com/github/grantjforrester/bdd/rest")
public class CucumberRestStepsTest {

	private static TestServerHelper testServer = new TestServerHelper();
	
	@BeforeClass
	public static void setUp() {
		testServer.expect()
				.get(and(by(uri("/test-get")), eq(header("inheader"), "inValue")))
				.response(with(text("someRepresentation")), header("outHeader", "outValue"));
		testServer.expect()
				.post(and(by(uri("/test-post")), eq(header("inheader"), "inValue"), by("someRepresentation")))
				.response(status(201), header("outHeader", "outValue"));
		testServer.start();
	}
	
	@AfterClass
	public static void tearDown() {
		testServer.stop();
	}
}
