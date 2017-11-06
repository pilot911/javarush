package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        Stack<File> stack = new Stack<>();

        File file = new File(root);

        stack.push(file);

        while (!stack.isEmpty()) {

            File f = (File)stack.pop();

            for (File ff : f.listFiles()) {
                if (ff.isDirectory()) {
                    stack.push(ff);
                } else {
                    System.out.println(ff.getAbsolutePath());
                    list.add(ff.getAbsolutePath());
                }
            }
        }

        return list;
    }

    public static void main(String[] args) throws Exception {
        Solution.getFileTree("D:\\DELETEME\\bootstrap-3.3.7-dist");
    }
}
