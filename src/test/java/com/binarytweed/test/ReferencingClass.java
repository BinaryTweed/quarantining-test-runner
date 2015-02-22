package com.binarytweed.test;


public class ReferencingClass
{
	public final ReferencedClass referenced;
	
	
	public ReferencingClass()
	{
		referenced = new ReferencedClass();
	}
	
	
	public String getReferencedMember()
	{
		return String.valueOf(ReferencedClass.VALUE);
	}
	
	
	public ClassLoader getReferencedClassLoader()
	{
		return ReferencedClass.class.getClassLoader();
	}
}
