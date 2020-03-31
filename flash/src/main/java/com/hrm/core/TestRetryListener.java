package com.hrm.core;

public class TestRetryListener implements IAnnotationTransformer {

    public void transform(final ITestAnnotation annotation, final Class testClass, final Constructor testConstructor,
            final Method testMethod) {
        IRetryAnalyzer retryAnalyzer = annotation.getRetryAnalyzer();
        if (retryAnalyzer == null) {
            annotation.setRetryAnalyzer(TestRetryAnalyzer.class);
        }
    }

}
