package com.javarush.task.task32.task3201;

import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws Exception {

        try(RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\DELETEME\\__111\\1.txt", "rw"))
        {
        }
    }
}
