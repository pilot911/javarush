package com.javarush.task.task22.task2212;

/* 
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null) return false;
        if (telNumber.isEmpty()) return false;
        int digits = telNumber.replaceAll("\\D", "").length();
        if ((telNumber.charAt(0) == '+' && digits == 12) || (telNumber.charAt(0) != '+' && digits == 10)) {
            return telNumber.matches("(\\+\\d+)?\\d*(\\(\\d{3}\\))?\\d+(-?\\d+){0,2}");
        }
        else return false;
    }

    public static void main(String[] args) {
        System.out.println("+380501234567 " + checkTelNumber("+380501234567") + " true");
        System.out.println("+38(050)12(050)34567 " + checkTelNumber("+38(050)1234567") + " true");
        System.out.println("+38050123-45-67 " + checkTelNumber("+38050123-45-67") + " true");
        System.out.println("050123-4567 " + checkTelNumber("050123-4567") + " true");
        System.out.println("+38)050(1234567 " + checkTelNumber("+38)050(1234567") + " false");
        System.out.println("+38(050)1-23-45-6-7 " + checkTelNumber("+38(050)1-23-45-6-7") + " false");
        System.out.println("050ххх4567 " + checkTelNumber("050ххх4567") + " false");
        System.out.println("050123456 " + checkTelNumber("050123456") + " false");
        System.out.println("(0)501234567 " + checkTelNumber("(0)501234567") + " false");
    }
}
