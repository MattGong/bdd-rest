package com.github.grantjforrester.bdd.rest.jbehave;

import org.junit.Test;

public class JBehaveCliTest {

	@Test
	public void shouldFindJBehaveRestStepsAndFeature() throws Exception {
		JBehaveCli.main(new String[]{"--steps", "com.github.grantjforrester.bdd.rest.jbehave", "src/test/resources/com/github/grantjforrester/bdd/rest/jbehave"});
		//JBehaveCli.runJBehave(new String[]{"com.github.grantjforrester.bdd.rest.jbehave"}, "src/test/resources/com/github/grantjforrester/bdd/rest/jbehave");
	}
}
