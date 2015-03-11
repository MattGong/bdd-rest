package com.github.grantjforrester.bdd.rest.jbehave;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.parsers.gherkin.GherkinStoryParser;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.ScanningStepsFactory;

public class JBehaveCli {

	private static final String includes = "*.feature";
	private static final String excludes = "";

	public static void main(String[] args) throws Exception {
		try {
			if (args.length < 2) usage();
			
			String[] stepPackageNames = getStepPackageNames(args);
			String featurePath = args[args.length - 1];
			
			System.out.println("Searching for steps in: " + Arrays.toString(stepPackageNames));
			System.out.println("Searching for features in: " + featurePath);
			
			runJBehave(stepPackageNames, featurePath);
		} catch (Throwable t) {
			System.out.println("Failing steps");
			System.exit(1);
		}
	}

	static void usage() {
		System.out.println("Usage: JBehaveCli --steps <stepPackage> [, --steps <stepPackage>]* <featurePath>");
		System.exit(1);
	}
	
	static String[] getStepPackageNames(String[] args) {
		OptionParser optionParser = new OptionParser();
		optionParser.accepts("steps").withRequiredArg();
		OptionSet options = optionParser.parse(args);
		List<?> steps = options.valuesOf("steps"); 
		return steps.toArray(new String[steps.size()]);
	}
	
	static void runJBehave(String[] stepPackageNames, String featurePath) throws MalformedURLException {
		Configuration config = new MostUsefulConfiguration()
				.useStoryLoader(new LoadFromRelativeFile(new File(featurePath).toURI().toURL()))
				.useStoryParser(new GherkinStoryParser())
				.useStoryReporterBuilder(new StoryReporterBuilder()
						.withDefaultFormats().withFormats(Format.CONSOLE));

		Embedder embedder = new Embedder();
		embedder.useConfiguration(config);
		embedder.useStepsFactory(new ScanningStepsFactory(config, stepPackageNames));
		embedder.runStoriesAsPaths(new StoryFinder().findPaths(featurePath, includes, excludes));
	}
}
