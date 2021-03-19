package com.gai.designpattern.proxy_pattern;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib 动态代理开始");
        Object invokeSuper = methodProxy.invokeSuper(o,objects);
        System.out.println("Cglib 动态代理结束");
        return invokeSuper;
    }
}

class CglibProxyDemo{
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealImage.class);
        enhancer.setCallback(cglibProxy);
        Image image = (Image) enhancer.create(new Class[]{String.class},new String[]{"cglib.jar"});
        image.display();
    }
}