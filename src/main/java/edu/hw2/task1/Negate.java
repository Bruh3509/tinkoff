package edu.hw2.task1;

public record Negate(double negateConstant) implements Expression {
    public Negate(Expression negateConstant) {
        this(negateConstant.evaluate());
    }

    @Override
    public double evaluate() {
        return -negateConstant;
    }
}
