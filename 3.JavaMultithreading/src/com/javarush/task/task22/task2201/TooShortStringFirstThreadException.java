package com.javarush.task.task22.task2201;

public class TooShortStringFirstThreadException extends RuntimeException {

    public TooShortStringFirstThreadException(Exception e) {
        super(e);
    }
}
