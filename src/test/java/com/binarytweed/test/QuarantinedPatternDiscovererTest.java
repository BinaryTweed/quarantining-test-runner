package com.binarytweed.test;

import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runners.JUnit4;

public class QuarantinedPatternDiscovererTest
{
	@Quarantine(value={"java.lang.Object", "com.binarytweed"})
	public static class Fixture
	{
	}
	
	
	@Quarantine(value={"java.lang.Object", "com.binarytweed"}, quarantineTestAndRunner=false)
	public static class TestAndRunnerExcludingFixture
	{
	}
	
	
	public static class UnannotatedFixture
	{
	}
	
	
	@Test
	public void getQuarantinedPatternsReturnsSpecifiedPatternsTestAndRunner()
	{
		QuarantinedPatternDiscoverer discoverer = new QuarantinedPatternDiscoverer();
		String[] discovered = discoverer.getQuarantinedPatternsOn(Fixture.class, JUnit4.class);
		assertThat(discovered, arrayContainingInAnyOrder
			(
				"java.lang.Object", 
				"com.binarytweed", 
				"com.binarytweed.test.QuarantinedPatternDiscovererTest$Fixture",
				"org.junit.runners.JUnit4"
			)
		);
	}
	
	
	@Test
	public void quarantineTestAndRunnerFalseExcludesTestAndRunner()
	{
		QuarantinedPatternDiscoverer discoverer = new QuarantinedPatternDiscoverer();
		String[] discovered = discoverer.getQuarantinedPatternsOn(TestAndRunnerExcludingFixture.class, JUnit4.class);
		assertThat(discovered, arrayContaining("java.lang.Object", "com.binarytweed"));
	}
	
	
	@Test
	public void unannotatedClassYieldsEmptyArray()
	{
		QuarantinedPatternDiscoverer discoverer = new QuarantinedPatternDiscoverer();
		String[] discovered = discoverer.getQuarantinedPatternsOn(UnannotatedFixture.class, JUnit4.class);
		assertThat(discovered, isA(String[].class));
		assertThat(discovered.length, is(0));
	}
}
