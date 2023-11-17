package edu.project3;

public sealed interface InfoPrinter permits ConsolePrinter {
    void print();
}
