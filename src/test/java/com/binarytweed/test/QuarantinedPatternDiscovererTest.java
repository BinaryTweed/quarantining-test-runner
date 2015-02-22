package com.binarytweed.test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.binarytweed.test.Quarantine;
import com.binarytweed.test.QuarantinedPatternDiscoverer;

public class QuarantinedPatternDiscovererTest
{
	@Quarantine({"java.lang.Object", "com.binarytweed"})
	public static class Fixture
	{
	}
	
	
	public static class UnannotatedFixture
	{
	}
	
	
	@Test
	public void getQuarantinedPatterns()
	{
		QuarantinedPatternDiscoverer discoverer = new QuarantinedPatternDiscoverer();
		String[] discovered = discoverer.getQuarantinedPatternsOn(Fixture.class);
		assertThat(discovered, arrayContaining("java.lang.Object", "com.binarytweed"));
	}
	
	
	@Test
	public void unannotatedClassYieldsEmptyArray()
	{
		QuarantinedPatternDiscoverer discoverer = new QuarantinedPatternDiscoverer();
		String[] discovered = discoverer.getQuarantinedPatternsOn(UnannotatedFixture.class);
		assertThat(discovered, isA(String[].class));
		assertThat(discovered.length, is(0));
	}
}
