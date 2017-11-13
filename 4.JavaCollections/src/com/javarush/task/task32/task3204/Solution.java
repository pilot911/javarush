package com.javarush.task.task32.task3204;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {

        try {
            StringBuilder stringBuilder = new StringBuilder();
            while (stringBuilder.length() < 6) {
                stringBuilder.append((char)getBigAlpha());
                stringBuilder.append((char)getSmallAlpha());
            }

            stringBuilder.append((char)getDigit());
            stringBuilder.append((char)getDigit());

            List<Character> list = stringBuilder.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
            Collections.shuffle(list, new SecureRandom());

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(list.size());

            String s = list.stream().map(i -> i.toString()).collect(Collectors.joining());

            byteArrayOutputStream.write(s.getBytes());
            return byteArrayOutputStream;
        } catch (Exception e) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(0);
            return byteArrayOutputStream;
        }
    }

    private static int getBigAlpha() {
        Random random = new Random();
        return random.nextInt(26) + 97;
    }

    private static int getSmallAlpha() {
        Random random = new Random();
        return random.nextInt(26) + 65;
    }

    private static int getDigit() {
        Random random = new Random();
        return random.nextInt(10) + 48;
    }
}
