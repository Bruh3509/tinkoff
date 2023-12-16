package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamCompositionTest {
    @Test
    @DisplayName("Stream Composition Test")
    void testStreamComposition() throws IOException {
        // arrange
        var path = Paths.get("src", "main", "resources", "testComposition.txt");

        // act
        StreamsComposition.writeToFile(path);
        var fileReader = new BufferedReader(new FileReader(path.toFile()));

        // assert
        assertTrue(Files.exists(path));
        assertThat(fileReader.readLine())
            .isEqualTo("Programming is learned by writing programs. ― Brian Kernighan");

        Files.delete(path);
    }
}
