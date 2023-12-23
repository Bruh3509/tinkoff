package edu.hw10.task1;

public class MyClass {
    private final String name;
    private final int age;

    public MyClass(@NotNull String name, @Min(70) int age) {
        this.name = name;
        this.age = age;
    }

    public static MyClass create(@NotNull String name, @Max(50) int age) {
        return new MyClass(name, age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
