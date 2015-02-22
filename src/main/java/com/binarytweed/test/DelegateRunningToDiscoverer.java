package com.binarytweed.test;

import org.junit.runner.Runner;
import org.junit.runners.BlockJUnit4ClassRunner;

public class DelegateRunningToDiscoverer
{
	public Class<? extends Runner> getDelegateRunningToOn(Class<?> testClass)
	{
		Class<? extends Runner> runnerClass = BlockJUnit4ClassRunner.class;
		DelegateRunningTo annotation = testClass.getAnnotation(DelegateRunningTo.class);
		
		if(annotation != null)
		{
			runnerClass = annotation.value();
		}
		
		return runnerClass;
	}
}
