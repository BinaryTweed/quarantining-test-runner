package com.binarytweed.test;

import java.util.Arrays;


public class QuarantinedPatternDiscoverer
{
	/**
	 * 
	 * @param annotatedClass class on which to look for {@code Quarantine} annotation
	 * @return values specified on the {@code Quarantine} annotation
	 */
	public String[] getQuarantinedPatternsOn(Class<?> annotatedClass, Class<?> delegateRunningToClass)
	{
		Quarantine annotation = annotatedClass.getAnnotation(Quarantine.class);
		
		if(annotation != null)
		{
			String[] specifiedPatterns = annotation.value();
			
			if(annotation.quarantineTestAndRunner())
			{
				String[] allPatterns = Arrays.copyOf(specifiedPatterns, specifiedPatterns.length + 2);
				allPatterns[specifiedPatterns.length] = annotatedClass.getName();
				allPatterns[specifiedPatterns.length + 1] = delegateRunningToClass.getName();
				specifiedPatterns = allPatterns;
			}
			
			return specifiedPatterns;
		}
		
		return new String[]{};
	}
}
