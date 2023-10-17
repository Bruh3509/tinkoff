package edu.hw2.task4;

public class WhoIsCalling {
    public static CallingInfo callingInfo() {
        Throwable e = new Throwable();
        StackTraceElement[] elements = e.getStackTrace();
        return new CallingInfo(elements[1].getClassName(), elements[1].getMethodName());
    }
}
