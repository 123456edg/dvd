package com.company.project.web;

public class StringDemo {
    public static void main(String[] args) {
        String s1 = "Runoob";              // String 直接创建
        String s2 = "Runoob";              // String 直接创建
        String s3 = s1;                    // 相同引用
        String s4 = new String("hello");   // String 对象创建
        String s5 = new String("world");   // String 对象创建
        System.out.println(s1.toLowerCase());
        StringBuffer sb = new StringBuffer("你好啊");
        System.out.println(sb.delete(0,1));
        
    }
}
