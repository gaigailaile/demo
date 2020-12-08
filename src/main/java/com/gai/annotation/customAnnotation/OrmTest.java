package com.gai.annotation.customAnnotation;

import java.lang.reflect.Field;

public class OrmTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = Class.forName("com.gai.annotation.customAnnotation.Student");
        StringBuffer sql = new StringBuffer();
        sql.append("create table ");
        Table table = (Table) clazz.getAnnotation(Table.class);
        sql.append(table.name() + " (");
        Field[] fields = clazz.getDeclaredFields();
        Boolean flag = false;
        for (int i = 0; i < fields.length; i++){
            Column column = fields[i].getAnnotation(Column.class);
            if(column == null){
                continue;
            }
            if(flag){
                sql.append(",");
            }
            String fieldName = column.name();
            int fieldLength = column.length();
            String type = column.type();
            sql.append(fieldName + " " + type + "(" + fieldLength+")");
            flag=true;
        }
        sql.append(");");
        System.out.println(sql.toString());
    }
}
