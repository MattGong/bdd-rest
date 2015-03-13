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

public class FeatureTests extends JUnitStories {
	private static final String glue = "com.github.grantjforrester.bdd.rest.jbehave";
	private static final String features = "features";
	private static final String includes = "*.feature";
	private static final String excludes = "";
	private Configuration configuration;
	
	public FeatureTests() throws Exception {
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
