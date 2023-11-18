package edu.project3;

public sealed interface InfoPrinter permits ConsolePrinter, MarkdownPrinter {
    void print();

    void printGeneralInformation();

    void printRequestedResources();

    void printStatusCodes();
}
