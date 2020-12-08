package com.gai.annotation.customAnnotation;

@Table(name = "t_student")
public class Student {
    @Column(name = "id",type = "int")
    private int id;

    @Column(name = "t_name",length = 64,type = "varchar")
    private String name;

    @Column(name = "t_age",type = "int")
    private int age;

    private int sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
