# quarantining-test-runner

[![Build Status](https://travis-ci.org/BinaryTweed/quarantining-test-runner.svg)](https://travis-ci.org/BinaryTweed/quarantining-test-runner)

A JUnit test runner that loads the <strong>test class</strong> and optionally specified classes in a separate `ClassLoader`. This means that static members will effectively be reset between each test class, as new versions of those classes are loaded.

## Maven dependency

```xml
<dependency>
  <groupId>com.binarytweed</groupId>
  <artifactId>quarantining-test-runner</artifactId>
  <version>0.0.3</version>
</dependency>
```

Check the latest version on [Maven Central](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.binarytweed%22%20a%3A%22quarantining-test-runner%22).


## To use

```java
import com.binarytweed.test.Quarantine;
import com.binarytweed.test.QuarantiningRunner;

@RunWith(QuarantiningRunner.class)
@Quarantine({"com.binarytweed", "com.example.ClassName"})
public class MyIsolatedTest {
...
```

1. Annotate your test class with `@RunWith(QuarantiningRunner.class)`.
1. Use `@Quarantine("com.example")` to specify patterns which separately-loaded class names should start with.
1. Optionally specify `@DelegateRunningTo(SomeCustomRunner.class)` to have `QuarantiningRunner` use another `Runner` implementation. By default it uses [`org.junit.runners.JUnit4`](http://junit.sourceforge.net/javadoc/org/junit/runners/JUnit4.html) (which currently extends `BlockJUnit4ClassRunner`).

## How it works

The test class and runner class are loaded using `QuarantiningUrlClassLoader`. When either of these two classes reference a class that hasn't yet been loaded, `QuarantiningUrlClassLoader` will be used to load the requested class. 

It will delegate loading classes that _do not match_ the patterns provided by `@Quarantined` to its parent `ClassLoader`; patterns that _do match_ are loaded by itself. Because each test run uses a a distinct instance of `QuarantiningUrlClassLoader`, any classes it loads are distinct from any previous runs.