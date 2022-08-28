package com.company.project.web;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Num{
    public static void main(String[] args){
        Date date1 = new Date();
        Long timeStamp = System.currentTimeMillis();
       
        SimpleDateFormat ft = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        String sd = ft.format(new Date(Long.parseLong(String.valueOf(timeStamp))));
        String sd1 = ft.format(timeStamp/1000);
        Date sd2 = new Date(timeStamp);
        System.out.println(date1.getTime());
        System.out.println(Calendar.getInstance().getTimeInMillis());
        System.out.println(ft.format(date1));
        System.out.println(sd);
        System.out.println(sd1);
        System.out.println(sd2);
        System.out.println(new Date(1624620006));
        
    }
   
}
