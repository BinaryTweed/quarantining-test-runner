package com.binarytweed.test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(QuarantiningRunner.class)
@Quarantine("com.binarytweed.test.ReferencingClass")
public class QuarantinedRunnerIntegrationTest
{
	@Test
	public void classLoadedByQuarantinedUrlClassLoader()
	{
		assertThat(getClass().getClassLoader(), instanceOf(QuarantiningUrlClassLoader.class));
	}
	
	
	@Test
	public void unspecifiedClassNotLoadedByQuarantinedUrlClassLoader()
	{
		Integer myInteger = new Integer(117);
		assertThat("Class not quarantined should be loaded by default classloader, which returns as null", myInteger.getClass().getClassLoader(), nullValue());
	}
	
	
	@Test
	public void specifiedClassLoadedByQuarantinedUrlClassLoader()
	{
		ReferencingClass instance = new ReferencingClass();
		assertThat(instance.getClass().getClassLoader(), instanceOf(QuarantiningUrlClassLoader.class));
	}
}
