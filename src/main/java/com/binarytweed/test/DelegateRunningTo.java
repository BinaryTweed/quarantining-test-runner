package com.binarytweed.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.runner.Runner;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Specifies the {@code Runner} that {@code QuarantiningRunner} delegates to. 
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DelegateRunningTo
{
	/**
	 * @return the {@code Runner} that {@code QuarantiningRunner} delegates to.
	 */
	public Class<? extends Runner> value() default BlockJUnit4ClassRunner.class;
}
