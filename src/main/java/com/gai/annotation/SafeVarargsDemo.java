package com.gai.annotation;

public class SafeVarargsDemo<S> {
    private S[] args;

    @SafeVarargs
    public SafeVarargsDemo(S... args) {
        this.args = args;
    }

    public static void main(String[] args) {
        SafeVarargsDemo.staticPrintArgs("A","B","C");
    }

    @SafeVarargs
    public final void finalPrintArgs(S... args){
        for (S info : args) {
            System.out.println(info);
        }
    }

    @SafeVarargs
    public static<S> void staticPrintArgs(S... args){
        for (S info : args) {
            System.out.println(info);
        }
    }
}
