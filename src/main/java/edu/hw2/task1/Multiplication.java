package edu.hw2.task1;

public record Multiplication(double firstOperand, double secondOperand) implements Expression {
    public Multiplication(Expression firstOperand, Expression secondOperand) {
        this(firstOperand.evaluate(), secondOperand.evaluate());
    }

    @Override
    public double evaluate() {
        return firstOperand * secondOperand;
    }
}
