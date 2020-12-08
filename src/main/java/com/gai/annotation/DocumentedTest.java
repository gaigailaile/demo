package com.gai.annotation;

@AnnotationDemo
public class DocumentedTest {
    @Override
    @AnnotationDemo
    public String toString() {
        return super.toString();
    }
}
