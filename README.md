# quarantining-test-runner

[![Build Status](https://travis-ci.org/BinaryTweed/quarantining-test-runner.svg)](https://travis-ci.org/BinaryTweed/quarantining-test-runner)

A JUnit test runner that loads the <strong>test class</strong> and optionally specified classes in a separate `ClassLoader`. This means that static members will effectively be reset between each test class, as new versions of those classes are loaded.

## Maven dependency

```xml
<dependency>
  <groupId>com.binarytweed</groupId>
  <artifactId>quarantining-test-runner</artifactId>
  <version>0.0.1</version>
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

1. Annotate your test class with `@RunWith(QuarantiningRunner.class)`
1. Use `@Quarantine("com.example")` to specify patterns which separately-loaded class names should start with 
1. Optionally specify `@DelegateRunningTo(SomeCustomRunner.class)` to have `QuarantiningRunner` use another `Runner` implementation. By default it uses `BlockJUnit4ClassRunner`

