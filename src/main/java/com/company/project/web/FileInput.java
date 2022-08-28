package com.company.project.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInput {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("D:\\dev\\dvd\\src\\main\\java\\com\\company\\project\\web\\test.txt");
            byte[] bytes = new byte[5];
            int count = fis.read(bytes);
            System.out.println(count);
            count = fis.read(bytes);
            System.out.println(count);
            count = fis.read(bytes);
            System.out.println(count);
            System.out.println(new String(bytes,0,count));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
