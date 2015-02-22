package com.binarytweed.test;

import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * If a class name starts with any of the supplied patterns, it is loaded by 
 * <em>this</em> classloader; otherwise it is loaded by the parent classloader.
 *
 */
public class QuarantiningUrlClassLoader extends URLClassLoader
{
	private static final Logger logger = LoggerFactory.getLogger(QuarantiningUrlClassLoader.class);
	
	
	private final Set<String> quarantinedClassNames;
	
	
	/**
	 * 
	 * @param quarantinedClassNames prefixes to match against when deciding how to load a class
	 */
	public QuarantiningUrlClassLoader(String... quarantinedClassNames)
	{
		super(((URLClassLoader) getSystemClassLoader()).getURLs());
		logger.trace("[{}] was loaded by [{}]", getClass().getName(), getClass().getClassLoader());
		
		this.quarantinedClassNames = new HashSet<>();
		for(String className : quarantinedClassNames)
		{
			this.quarantinedClassNames.add(className);
		}
	}


	/**
	 * If a class name starts with any of the supplied patterns, it is loaded by 
	 * <em>this</em> classloader; otherwise it is loaded by the parent classloader.
	 * 
	 * @param name class to load
	 */
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException
	{
		boolean quarantine = false;
		
		for(String quarantinedPattern : quarantinedClassNames)
		{
			if(name.startsWith(quarantinedPattern))
			{
				quarantine = true;
				break;
			}
		}
		
		if(quarantine)
		{
			logger.debug("Detected quarantined class [{}]", name);
			try
			{
				return findClass(name);
			}
			catch (ClassNotFoundException e)
			{
				logger.error("Could not load [{}]", name);
				throw e;
			}
		}

		logger.trace("[{}] being loaded by parent", name);
		return super.loadClass(name);
	}
}