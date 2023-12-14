package edu.hw10.task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        MyProxy.FibCalc fibCalc = new MyProxy.FibCalc();
        MyProxy.Handler handler = new MyProxy.Handler(fibCalc);
        FibCalculator calc = (FibCalculator) Proxy.newProxyInstance(
            FibCalculator.class.getClassLoader(),
            new Class[] {FibCalculator.class},
            handler
        );

        System.out.println(calc.fib(10));
        System.out.println(calc.fib(10));
    }
}
