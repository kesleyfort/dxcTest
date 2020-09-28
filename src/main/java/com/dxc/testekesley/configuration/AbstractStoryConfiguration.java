package com.dxc.testekesley.configuration;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.jbehave.web.selenium.WebDriverHtmlOutput;
import org.springframework.context.ApplicationContext;

import java.util.Locale;

public abstract class AbstractStoryConfiguration extends JUnitStories {

	protected ApplicationContext context;
	
	public AbstractStoryConfiguration() {
		context = getAnnotatedApplicationContext();
		
		Embedder embedder = configuredEmbedder();
		String DEFAULT_STORY_TIMEOUT_SECS = "7200";
		embedder.embedderControls().doIgnoreFailureInStories(true)
									.useStoryTimeouts(DEFAULT_STORY_TIMEOUT_SECS)
									.doFailOnStoryTimeout(false)
									.doGenerateViewAfterStories(true)
									.doIgnoreFailureInView(false)
									.doVerboseFailures(true);
	}
	
	@Override
	public Configuration configuration() {

		StoryReporterBuilder reporterBuilder = new StoryReporterBuilder().withFormats(storyFormat())
													.withFailureTraceCompression(true);
		Keywords keywords = new LocalizedKeywords(new Locale("pt_br"));

		Configuration configuration = new MostUsefulConfiguration().useStoryReporterBuilder(reporterBuilder)
				.useStoryControls(new StoryControls().doResetStateBeforeScenario(true))
				.useKeywords(keywords)
				.useParameterControls(new ParameterControls().useDelimiterNamedParameters(true));

		return configuration;
	}
	@Override
	public InjectableStepsFactory stepsFactory() {
		return new SpringStepsFactory(configuration(), context);
	}
	
	protected Format[] storyFormat() {
		Format[] formats = new Format[] { Format.IDE_CONSOLE, Format.STATS, WebDriverHtmlOutput.WEB_DRIVER_HTML };
		return formats;
	}
	
	public abstract ApplicationContext getAnnotatedApplicationContext();
}