package com.binarytweed.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AnnotationInheritanceTest
{
	@Quarantine({"com.foo.bar"})
	@DelegateRunningTo(QuarantiningRunner.class)
	public static class AbstractQuarantineTest
	{
	}
	
	public static class ConcreteQuarantineTest extends AbstractQuarantineTest
	{
	}
	
	
	@Test
	@SuppressWarnings("rawtypes")
	public void extendedTestInheritsAnnotations()
	{
		DelegateRunningTo delegateRunningTo = ConcreteQuarantineTest.class.getAnnotation(DelegateRunningTo.class);
		Quarantine quarantine = ConcreteQuarantineTest.class.getAnnotation(Quarantine.class);
		
		assertThat(delegateRunningTo, notNullValue());
		assertThat(delegateRunningTo.value(), equalTo((Class) QuarantiningRunner.class));
		
		assertThat(quarantine, notNullValue());
		assertThat(quarantine.value(), is(new String[]{"com.foo.bar"}));
	}
}
