# quarantining-test-runner

[![Build Status](https://travis-ci.org/BinaryTweed/quarantining-test-runner.svg)](https://travis-ci.org/BinaryTweed/quarantining-test-runner)

A JUnit test runner that loads the <strong>test class</strong> and optionally specified classes in a separate `ClassLoader`. This means that static members will effectively be reset between each test class, as new versions of those classes are loaded.

## To use

```java
@RunWith(QuarantiningRunner.class)
@Quarantine({"com.binarytweed", "com.example.ClassName"})
public class MyIsolatedTest {
...
```

1. Annotate your test class with `@RunWith(QuarantiningRunner.class)`.
1. Use `@Quarantine("com.example")` to specify patterns which separately-loaded class names should start with.
1. Optionally specify `@DelegateRunningTo(SomeCustomRunner.class)` to have `QuarantiningRunner` use another `Runner` implementation. By default it uses [`org.junit.runners.JUnit4`](http://junit.sourceforge.net/javadoc/org/junit/runners/JUnit4.html) (which currently extends `BlockJUnit4ClassRunner`).

