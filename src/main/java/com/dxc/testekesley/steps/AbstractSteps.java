package com.dxc.testekesley.steps;

import org.springframework.beans.factory.annotation.Autowired;

import com.dxc.testekesley.context.TestContext;

public abstract class AbstractSteps {
	
	@Autowired
	protected TestContext context;
}
