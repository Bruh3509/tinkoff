package edu.hw2;

public sealed interface Expression
    permits Constant, Negate, Exponent, Addition, Multiplication {
    double evaluate();
}
