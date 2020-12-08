package com.gai.annotation;

public class InheritedAnnotationTest extends parent {
    public static void main(String[] args) {
        Class<InheritedAnnotationTest> child=InheritedAnnotationTest.class;
        InheritedAnnotation annotation = child.getAnnotation(InheritedAnnotation.class);
        System.out.println(annotation.name());
    }
}

@InheritedAnnotation(name = "yanghui")
class parent{

}