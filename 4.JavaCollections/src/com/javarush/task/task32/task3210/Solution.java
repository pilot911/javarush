package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws Exception {
        String filename = args[0];
        Long pos = Long.parseLong(args[1]);
        String text = args[2];

        try(RandomAccessFile randomAccessFile = new RandomAccessFile(filename, "rw")) {

            randomAccessFile.seek(pos);

            if (randomAccessFile.length() < pos) {
                randomAccessFile.seek(randomAccessFile.length());
                randomAccessFile.write("false".getBytes());
            } else {

                byte[] b = new byte[text.getBytes().length];

                randomAccessFile.read(b, 0, b.length);

                String readText = new String(b);

                randomAccessFile.seek(randomAccessFile.length());

                if (readText.equals(text)) {
                    randomAccessFile.write("true".getBytes());
                } else {
                    randomAccessFile.write("false".getBytes());
                }
            }
        }
    }
}
