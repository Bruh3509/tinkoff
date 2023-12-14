package edu.hw10.task1;

public class Main {
    public static void main(String[] args) throws Throwable {
        var rand = new RandomObjectGenerator();
        var myClass = (MyClass) rand.nextObject(MyClass.class);
        var myRecord = (MyRecord) rand.nextObject(MyRecord.class);

        System.out.printf("Name: %s, age: %s\n", myClass.getName(), myClass.getAge());
        System.out.printf("Name: %s, age: %s\n", myRecord.name(), myRecord.age());
    }
}
