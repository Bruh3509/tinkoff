package edu.hw2;

public record Constant(double constant) implements Expression {
    @Override
    public double evaluate() {
        return this.constant;
    }
}
