package com.javarush.task.task32.task3206;

import java.lang.reflect.Proxy;
import java.math.BigInteger;

/* 
Дженерики для создания прокси-объекта
*/
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        test(solution.getProxy(Item.class));                        //true false false
        test(solution.getProxy(Item.class, Small.class));           //true false true
        test(solution.getProxy(Item.class, Big.class, Small.class));//true true true
        test(solution.getProxy(Big.class, Small.class));            //true true true т.к. Big наследуется от Item
        test(solution.getProxy(Small.class, Big.class));                         //true true false т.к. Big наследуется от Item
    }

    public <T extends Item> T getProxy(Class<?> clz, Class ...clazz) {

        ItemInvocationHandler itemInvocationHandler = new ItemInvocationHandler();
        ClassLoader classLoader = clz.getClassLoader();

        Class[] interfaces = new Class[clazz.length + 1];
        interfaces[0] = clz;

        System.arraycopy(clazz, 0, interfaces, 1, clazz.length);

        return (T) Proxy.newProxyInstance(classLoader, interfaces, itemInvocationHandler);
    }


    private static void test(Object proxy) {
        boolean isItem = proxy instanceof Item;
        boolean isBig = proxy instanceof Big;
        boolean isSmall = proxy instanceof Small;

        System.out.format("%b %b %b\n", isItem, isBig, isSmall);
    }
}