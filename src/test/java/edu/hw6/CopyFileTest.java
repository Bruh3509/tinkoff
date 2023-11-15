package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CopyFileTest {
    @Test
    @DisplayName("Test Task2")
    void testTask2() throws IOException { // TODO testing
        // arrange
        Path path = Paths.get(".", "src", "main", "resources", "test.txt");
        Path file;
        if (!Files.exists(path)) {
            file = Files.createFile(path);
        } else {
            file = new File(path.toString()).toPath();
        }

        // act
        CopyFile.cloneFile(file.toAbsolutePath());
        CopyFile.cloneFile(file.toAbsolutePath());

        // assert
        assertTrue(Files.exists(Paths.get(".", "src", "main", "resources", "test - copy (1).txt")));
        assertTrue(Files.exists(Paths.get(".", "src", "main", "resources", "test - copy (2).txt")));
    }
}
