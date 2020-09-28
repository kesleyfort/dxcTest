package com.dxc.testekesley;

import java.util.List;

import com.dxc.testekesley.configuration.ProjectConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dxc.testekesley.configuration.AbstractStoryConfiguration;

public class AllStories extends AbstractStoryConfiguration {

	@Override
	public ApplicationContext getAnnotatedApplicationContext() {
		return new AnnotationConfigApplicationContext(ProjectConfiguration.class);
	}
	
	@Override
	public List<String> storyPaths() {
		List<String> paths = new StoryFinder().findPaths(
				CodeLocations.codeLocationFromClass(this.getClass()), "**/stories/*.story", "");
		return paths;
	}
}