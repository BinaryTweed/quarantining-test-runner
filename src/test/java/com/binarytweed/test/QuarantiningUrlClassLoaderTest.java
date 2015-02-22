package com.binarytweed.test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

@SuppressWarnings({"resource", "unchecked"})
public class QuarantiningUrlClassLoaderTest
{
	@Test
	public void quarantinedClassIsLoadedByUs() throws Exception
	{
		QuarantiningUrlClassLoader loader = new QuarantiningUrlClassLoader("com.binarytweed");
		Class<?> quarantinedClass = loader.loadClass("com.binarytweed.test.ReferencingClass");
		assertThat(quarantinedClass.getClassLoader(), equalTo((ClassLoader) loader));
	}
	
	
	@Test
	public void otherClassIsLoadedByParent() throws Exception
	{
		QuarantiningUrlClassLoader loader = new QuarantiningUrlClassLoader("com.binarytweed");
		Class<?> otherClass = loader.loadClass("java.sql.Timestamp");
		assertThat(otherClass.getClassLoader(), nullValue());
	}
	
	
	@Test
	public void settingStaticVarBeforeIsolatedLoad() throws Exception
	{
		ReferencedClass.VALUE = 100;
		ReferencingClass instance = new ReferencingClass();
		
		QuarantiningUrlClassLoader loader = new QuarantiningUrlClassLoader(ReferencingClass.class.getName(), ReferencedClass.class.getName());
		
		
		Class<ReferencingClass> isolated = (Class<ReferencingClass>) loader.loadClass(ReferencingClass.class.getName());
		
		assertThat((Class<ReferencingClass>) instance.getClass(), not(equalTo(isolated)));
		assertThat((String) isolated.getDeclaredMethod("getReferencedMember").invoke(isolated.newInstance()), is("0"));
	}
}
