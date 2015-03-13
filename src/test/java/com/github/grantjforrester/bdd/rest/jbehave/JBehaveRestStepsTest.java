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
package com.github.grantjforrester.bdd.rest.jbehave;

import static com.github.dreamhead.moco.Moco.and;
import static com.github.dreamhead.moco.Moco.by;
import static com.github.dreamhead.moco.Moco.eq;
import static com.github.dreamhead.moco.Moco.header;
import static com.github.dreamhead.moco.Moco.status;
import static com.github.dreamhead.moco.Moco.text;
import static com.github.dreamhead.moco.Moco.uri;
import static com.github.dreamhead.moco.Moco.with;

import java.io.File;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.parsers.gherkin.GherkinStoryParser;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ScanningStepsFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.github.grantjforrester.bdd.rest.TestServerHelper;

public class JBehaveRestStepsTest extends JUnitStories {
	private static final String glue = "com.github.grantjforrester.bdd.rest.jbehave";
	private static final String features = "src/test/resources/com/github/grantjforrester/bdd/rest";
	private static final String includes = "*.feature";
	private static final String excludes = "";
	private static TestServerHelper testServer = new TestServerHelper();
	private Configuration configuration;
	
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
	
	public JBehaveRestStepsTest() throws Exception {
		configuration = new MostUsefulConfiguration()
				.useStoryParser(new GherkinStoryParser())
				.useStoryLoader(new LoadFromRelativeFile(new File(features).toURI().toURL()))
				.useStoryReporterBuilder(new StoryReporterBuilder()
						.withDefaultFormats().withFormats(Format.CONSOLE));
	}
	
	@Override
	public Configuration configuration() {
		return configuration;
	}
	
	@Override
	public InjectableStepsFactory stepsFactory() {
		return new ScanningStepsFactory(configuration, glue);
	}
	
	@Override
	protected List<String> storyPaths() {
		return new StoryFinder().findPaths(features, includes, excludes);
	}
}
