package edu.hw2;

public record Addition(double firstOperand, double secondOperand) implements Expression {
    public Addition(Expression firstOperand, Expression secondOperand) {
        this(firstOperand.evaluate(), secondOperand.evaluate());
    }
    @Override
    public double evaluate() {
        return firstOperand + secondOperand;
    }
}
