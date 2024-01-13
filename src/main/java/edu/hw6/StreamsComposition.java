package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class StreamsComposition {
    public static void writeToFile(Path path) throws IOException {
        try (var file =
                 new PrintWriter(
                     new OutputStreamWriter(
                         new BufferedOutputStream(
                             new CheckedOutputStream(
                                 new FileOutputStream(Files.createFile(path).toFile()), new Adler32())),
                         StandardCharsets.UTF_8
                     ))) {
            file.write("Programming is learned by writing programs. ― Brian Kernighan");
        }
    }

    private StreamsComposition() {
    }
}
