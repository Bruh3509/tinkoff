package edu.hw10.task1;

public record MyRecord(String name, @Max(20) int age) {
}
