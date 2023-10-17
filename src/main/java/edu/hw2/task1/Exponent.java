package edu.hw2.task1;

public record Exponent(double base, double exponent) implements Expression {
    public Exponent(Expression base, Expression exponent) {
        this(base.evaluate(), exponent.evaluate());
    }

    @Override
    public double evaluate() {
        return Math.pow(base, exponent);
    }
}
