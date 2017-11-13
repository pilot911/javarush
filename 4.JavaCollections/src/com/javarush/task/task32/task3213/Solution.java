package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor Dpljr");
        System.out.println(decode(reader, -3));  //Hello Amigo

    }

    public static String decode(StringReader reader, int key) throws IOException {

        StringBuffer stringBuffer = new StringBuffer();

        int a;

        try {
            while ((a = reader.read()) != -1) {
                stringBuffer.append(Character.toString((char)(a + key)));
            }
        } catch (Exception e) {}

        return stringBuffer.toString();
    }
}
