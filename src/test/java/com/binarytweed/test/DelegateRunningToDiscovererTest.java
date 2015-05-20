package com.binarytweed.test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.JUnit4;

@SuppressWarnings("rawtypes")
public class DelegateRunningToDiscovererTest
{
	@DelegateRunningTo(QuarantiningRunner.class)
	public static class FixtureWithValue
	{
	}
	
	
	@DelegateRunningTo
	public static class FixtureWithoutValue
	{
	}
	
	
	public static class UnannotatedFixture
	{
	}
	
	
	@Test
	public void annotatedClassWithValueReturnsSpecifiedValue()
	{
		DelegateRunningToDiscoverer discoverer = new DelegateRunningToDiscoverer();
		Class discovered = discoverer.getDelegateRunningToOn(FixtureWithValue.class);
		assertThat(discovered, equalTo((Class) QuarantiningRunner.class));
	}
	
	
	@Test
	public void annotatedClassWithoutValueReturnsDefault()
	{
		DelegateRunningToDiscoverer discoverer = new DelegateRunningToDiscoverer();
		Class discovered = discoverer.getDelegateRunningToOn(FixtureWithoutValue.class);
		assertThat(discovered, equalTo((Class) JUnit4.class));
	}
	
	
	@Test
	public void unannotatedClassReturnsDefault()
	{
		DelegateRunningToDiscoverer discoverer = new DelegateRunningToDiscoverer();
		Class discovered = discoverer.getDelegateRunningToOn(UnannotatedFixture.class);
		assertThat(discovered, equalTo((Class) JUnit4.class));
	}
}
