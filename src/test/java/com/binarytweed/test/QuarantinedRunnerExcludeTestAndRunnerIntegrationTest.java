package com.binarytweed.test;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(QuarantiningRunner.class)
@Quarantine(value={"com.binarytweed.test.ReferencingClass"}, quarantineTestAndRunner=false)
public class QuarantinedRunnerExcludeTestAndRunnerIntegrationTest
{
	@Test
	public void classNotLoadedByQuarantinedUrlClassLoader()
	{
		assertThat(getClass().getClassLoader(), not(instanceOf(QuarantiningUrlClassLoader.class)));
	}
	
	
	@Test
	public void specifiedClassLoadedByQuarantinedUrlClassLoader()
	{
		ReferencingClass instance = new ReferencingClass();
		assertThat(instance.getClass().getClassLoader(), instanceOf(QuarantiningUrlClassLoader.class));
	}

	
	@Test
	public void packagePrivateReferencesWork()
	{
		ReferencedClass referenced = new ReferencedClass();
		assertThat(referenced.getFoo(), is("foo"));
	}
}
