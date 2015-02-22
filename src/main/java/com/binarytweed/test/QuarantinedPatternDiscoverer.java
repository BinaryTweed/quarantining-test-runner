package com.binarytweed.test;


public class QuarantinedPatternDiscoverer
{
	/**
	 * 
	 * @param annotatedClass class on which to look for {@code Quarantine} annotation
	 * @return values specified on the {@code Quarantine} annotation
	 */
	public String[] getQuarantinedPatternsOn(Class<?> annotatedClass)
	{
		Quarantine annotation = annotatedClass.getAnnotation(Quarantine.class);
		
		if(annotation != null)
		{
			return annotation.value();
		}
		
		return new String[]{};
	}
}
