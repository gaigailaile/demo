package com.gai.annotation;

@FunctionalInterface
public interface FullName {
    void sayName();

    @Override
    boolean equals(Object obj);
}
