package com.gai.designpattern.proxypattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy implements InvocationHandler{
    public Object target;

    public JdkProxy(Object target){
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(target,args);
        return invoke;
    }
}

class JdkProxyDemo{
    public static void main(String[] args) {
        RealImage realImage = new RealImage("a.txt");
        InvocationHandler jdkProxy = new JdkProxy(realImage);
        Image o = (Image) Proxy.newProxyInstance(realImage.getClass().getClassLoader(), realImage.getClass().getInterfaces(),jdkProxy);
        o.display();
    }
}