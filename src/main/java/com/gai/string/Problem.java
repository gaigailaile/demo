package com.gai.string;

public class Problem {

    /*
    *   请实现一个函数，把字符串中的每个空格替换成"%20"。
    *   例如输入“We are happy.”，则输出“We%20are%20happy.”。
    * */
    public static String problem1(String str){
        //利用Java api做的
//        if(str == null){
//            return str;
//        }
//        return str.replaceAll(" ","%20");

        //正常思路
        //1.创建一个可变字符传
        //2.遍历原字符传将空格替换为%20
        if(str == null){
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++){
            if(" ".equals(String.valueOf(str.charAt(i)))){
                stringBuffer.append("%20");
            }else {
                stringBuffer.append(str.charAt(i));
            }
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String str = "We are happy.";
        str = Problem.problem1(str);
        System.out.println(str);
    }
}
