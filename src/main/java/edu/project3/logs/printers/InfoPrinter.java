package edu.project3.logs.printers;

public sealed interface InfoPrinter permits ConsolePrinter, MarkdownPrinter, AdocPrinter {
    void print();

    void printGeneralInformation();

    void printRequestedResources();

    void printStatusCodes();
}
