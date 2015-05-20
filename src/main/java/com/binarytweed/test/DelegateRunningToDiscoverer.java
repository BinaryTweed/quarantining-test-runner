package com.binarytweed.test;

import org.junit.runner.Runner;
import org.junit.runners.JUnit4;

public class DelegateRunningToDiscoverer
{
	public Class<? extends Runner> getDelegateRunningToOn(Class<?> testClass)
	{
		Class<? extends Runner> runnerClass = JUnit4.class;
		DelegateRunningTo annotation = testClass.getAnnotation(DelegateRunningTo.class);
		
		if(annotation != null)
		{
			runnerClass = annotation.value();
		}
		
		return runnerClass;
	}
}
