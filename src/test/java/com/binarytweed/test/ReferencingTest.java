package com.binarytweed.test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Test;

/**
 * Here to prove how classloaders actualy work!
 *
 */
@SuppressWarnings({"resource", "unchecked"})
public class ReferencingTest
{
	@Test
	public void settingStaticVarBeforeIsolatedLoad() throws Exception
	{
		ReferencedClass.VALUE = 100;
		ReferencingClass instance = new ReferencingClass();
		
		URL[] systemUrls = ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs();
		URLClassLoader loader = new URLClassLoader(systemUrls, null);
		
		Class<ReferencingClass> isolated = (Class<ReferencingClass>) loader.loadClass("com.binarytweed.test.ReferencingClass");
		
		assertThat((Class<ReferencingClass>) instance.getClass(), not(equalTo(isolated)));
		
		assertThat((String) isolated.getDeclaredMethod("getReferencedMember").invoke(isolated.newInstance()), is("0"));
	}
	
	
	@Test
	public void settingStaticVarAfterIsolatedLoad() throws Exception
	{		
		URL[] systemUrls = ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs();
		URLClassLoader loader = new URLClassLoader(systemUrls, null);
		
		Class<ReferencingClass> isolated = (Class<ReferencingClass>) loader.loadClass("com.binarytweed.test.ReferencingClass");
		assertThat((String) isolated.getDeclaredMethod("getReferencedMember").invoke(isolated.newInstance()), is("0"));
		
		ReferencedClass.VALUE = 100;
		assertThat((String) isolated.getDeclaredMethod("getReferencedMember").invoke(isolated.newInstance()), is("0"));
	}
	
	
	@Test
	public void classLoaderOfDeclaredClassIsDifferent() throws Exception
	{
		ReferencingClass instance = new ReferencingClass();
		
		URL[] systemUrls = ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs();
		URLClassLoader loader = new URLClassLoader(systemUrls, null);
		
		Class<ReferencingClass> isolated = (Class<ReferencingClass>) loader.loadClass("com.binarytweed.test.ReferencingClass");
		
		assertThat((ClassLoader) isolated.getDeclaredMethod("getReferencedClassLoader").invoke(isolated.newInstance()), not(instance.getReferencedClassLoader()));
	}
}
