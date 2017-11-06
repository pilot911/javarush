package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.*;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();

        HashSet<String> hash1 = new HashSet<>();
        HashSet<String> hash2 = new HashSet<>();

        try (BufferedReader filebBufferedReader = new BufferedReader(new FileReader(file))) {

            while (filebBufferedReader.ready()) {
                String s = filebBufferedReader.readLine();
                String sReverse = new StringBuilder(s).reverse().toString();

                if (hash1.contains(s) && hash2.contains(sReverse)) {
                    result.add(new Pair(s, sReverse));
                } else if (hash1.contains(sReverse) && hash2.contains(s)) {
                    result.add(new Pair(s, sReverse));
                } else if (!hash1.contains(s)) {
                    hash1.add(s);
                }
            }

        } catch (Exception e) {

        }
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
