package com.gai.annotation;

public class FunctionaInterfaceDemo {
    public static void show(FullName fullName){
        fullName.sayName();
    };

    public static void main(String[] args) {
        People people = new People();
        people.setName("gaidongxu");
        //show方法参数是接口、第一种方式直接传递接口的实现类
        show(people);

        //show方法参数是接口、第二种方式传递匿名内部类
        show(new FullName() {
            @Override
            public void sayName() {
                System.out.println("匿名内部类输出 gaidongux");
            }
        });

        //show方法参数是函数接口、第三种方式lambda表达式
        show(()->{System.out.println("lambda表达式输出 gaidongxu");});

        //简化lambda表达式
        show(()->System.out.println("简化lambda表达式输出 gaidongxu"));
    }
}
