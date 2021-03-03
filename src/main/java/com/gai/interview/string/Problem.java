package com.gai.interview.string;

import java.util.ArrayList;
import java.util.Collections;

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

    /*
    *   请实现一个函数用来匹配包括’.’和’*‘的正则表达式。
    *   模式中的字符’.’表示任意一个字符， 而* 表示它前面的字符可以出现任意次（包含 0 次）
    *
    * */
    public static boolean problem2(String str,String pattern){
        if(str == null || pattern == null){
            return false;
        }
        if(str.length() == 1 && pattern.length() == 1){
            if(str.equals(pattern) || ".".equals(pattern)){
                return true;
            }
            return false;
        }
        int sIndex = 0;
        int pIndex = 0;
        return matchIndex(str.toCharArray(),sIndex,pattern.toCharArray(),pIndex);
    }

    public static boolean matchIndex(char[] str,int sIndex,char[] pattern,int pIndex){
        if(sIndex == str.length && pIndex == pattern.length){
            return true;
        }
        if(sIndex != str.length && pIndex == pattern.length){
            return false;
        }
        if(pIndex + 1 < pattern.length && '*' == pattern[pIndex+1]){
            if(sIndex != str.length && (pattern[pIndex] == str[sIndex] || '.' == pattern[pIndex])){
                return matchIndex(str,sIndex,pattern,pIndex+2)
                        || matchIndex(str,sIndex+1,pattern,pIndex+2)
                        || matchIndex(str,sIndex+1,pattern,pIndex);
            }else {
                return  matchIndex(str,sIndex,pattern,pIndex+2);
            }
        }

        if(sIndex != str.length && (pattern[pIndex] == str[sIndex] || '.' == pattern[pIndex])){
            return matchIndex(str,sIndex+1,pattern,pIndex+1);
        }
        return false;
    }

    /*
    *   请实现一个函数用来判断字符串是否表示数值（包括整数和小数）
    * */
    public static boolean problem3(String str){
        char[] chars = str.toCharArray();
        if(str == null){
            return false;
        }
        //记录E或e出现的次数
        int eCount = 0;
        //记录小数点出现的次数
        int pCount = 0;
        for (int i = 0; i < chars.length; i++){
            //第一位是-+跳过，中间出现-+判断前一位是否为E或e。跳过
            if(chars[i] == '+' || chars[i] == '-'){
                if(i != 0 && (chars[i-1] != 'e' && chars[i-1] != 'E')){
                    return false;
                }
                continue;
            }
            //判断E或e出现的位置（不能以E或e开头收尾）、判断E或e前一位是否为数字 跳过
            if(chars[i] == 'e' || chars[i] == 'E'){
                eCount++;
                if(eCount > 1){
                    return false;
                }
                if(i == 0 || i == chars.length-1 || chars[i-1] < 48 || chars[i-1] > 57){
                    return false;
                }
                pCount++;
                continue;
            }
            //判断小数点出现的次数、是否出现在E或e后面 跳过
            if(chars[i] == '.'){
                pCount++;
                if(pCount > 1){
                    return false;
                }
                continue;
            }
            //判断是否出现了除了E和e之外的特殊字符（-、+、.在走到这里之前已经处理过了）
            if((chars[i] < 48 || chars[i] > 57) && (chars[i] != 'e' && chars[i] == 'E')){
                return false;
            }
        }
        return true;
    }

    /*
    *   输入一个字符串,按字典序打印出该字符串中字符的所有排列。
    *   例如输入字符串 abc,则打印出由字符 a,b,c 所能排列出来的所有字符串 abc,acb,bac,bca,cab和cba。
     * */
    public static ArrayList<String> problem4(String str){
        ArrayList<String> list = new ArrayList<>();
        if(str != null && str.length() != 0){
            helper(str.toCharArray(), 0, list);
//            Collections.sort(list);//对所得的list集合进行排序
        }
        return list;
    }

    public static void helper(char[] chars, int i, ArrayList<String> list){
        if(i == chars.length - 1){
            String value = String.valueOf(chars);
            //如果list集合中不包含这个字符串，则添加（避免重复）
            if(!list.contains(value)){
                list.add(value);
            }
        }else {
            for (int j = i; j < chars.length; j++){
                //交换数组第i个字符和第j个字符
                swap(chars,i,j);
                helper(chars,i + 1,list);
                //再次交换数组第i个字符和第j个字符，保证回到此次for循环前字符数组的状态，不影响字符数组进行下一次for循环
                swap(chars,i,j);
            }
        }
    }

    public static void swap(char[] chars, int i, int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        ArrayList<String> list = problem4("abc");
        for (String str:list) {
            System.out.print(str + " ");
        }
    }
}
