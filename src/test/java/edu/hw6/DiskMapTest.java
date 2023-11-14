package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiskMapTest {
    @Test
    @DisplayName("DiskMap Test Put")
    void testPut() throws IOException {
        // arrange
        var file = File.createTempFile("test_put", ".txt");
        var diskMap = new DiskMap(file);
        BufferedReader reader;

        // act
        diskMap.put("1", "word");
        diskMap.put("2", "done");

        // assert
        assertThat(diskMap).containsAllEntriesOf(Map.ofEntries(
            Map.entry("1", "word"),
            Map.entry("2", "done")
        ));

        reader = new BufferedReader(new FileReader(file));
        assertThat(readFile(reader)).containsExactlyInAnyOrder("1:word", "2:done");

        // section3

        assertFalse(diskMap.isEmpty());
        assertTrue(diskMap.containsKey("3"));

    }

    @Test
    @DisplayName("Test Remove")
    void testRemove() throws IOException {
        // arrange
        var file = File.createTempFile("test_remove", ".txt");
        var diskMap = new DiskMap(file);
        BufferedReader reader;

        // act
        diskMap.put("1", "word");
        diskMap.put("2", "done");
        diskMap.remove("1");

        // assert
        assertThat(diskMap).containsAllEntriesOf(Map.ofEntries(
            Map.entry("2", "done")
        ));

        reader = new BufferedReader(new FileReader(file));
        assertThat(readFile(reader)).containsExactlyInAnyOrder("2:done");
    }

    @Test
    @DisplayName("Test Get")
    void testGet() throws IOException {
        // arrange
        var file = File.createTempFile("test_get", ".txt");
        var diskMap = new DiskMap(file);
        BufferedReader reader;

        // act
        diskMap.put("1", "word");
        diskMap.put("2", "done");

        // assert
        diskMap.put("3", "GPU");
        var getValue = diskMap.get("3");

        assertThat(getValue).isEqualTo("GPU");

        reader = new BufferedReader(new FileReader(file));
        assertThat(readFile(reader)).containsExactlyInAnyOrder("3:GPU", "2:done", "1:word");
    }

    @Test
    @DisplayName("Test Size")
    void testSize() throws IOException {
        // arrange
        var file = File.createTempFile("test_size", ".txt");
        var diskMap = new DiskMap(file);
        BufferedReader reader;

        // act
        diskMap.put("1", "word");
        diskMap.put("2", "done");

        // assert
        assertThat(diskMap.size()).isEqualTo(2);

        reader = new BufferedReader(new FileReader(file));
        assertThat(readFile(reader).size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Test IsEmptyClear")
    void testIsEmptyClear() throws IOException {
        // arrange
        var file = File.createTempFile("test_size", ".txt");
        var diskMap = new DiskMap(file);
        BufferedReader reader;

        // act
        diskMap.put("1", "word");
        diskMap.put("2", "done");

        // assert
        assertFalse(diskMap.isEmpty());
        reader = new BufferedReader(new FileReader(file));
        assertFalse(readFile(reader).isEmpty());

        diskMap.clear();
        assertTrue(diskMap.isEmpty());
        reader = new BufferedReader(new FileReader(file));
        assertTrue(readFile(reader).isEmpty());
    }

    private List<String> readFile(BufferedReader reader) throws IOException {
        var readLine = reader.readLine();
        List<String> result = new ArrayList<>();
        while (readLine != null) {
            result.add(readLine);
            readLine = reader.readLine();
        }

        reader.close();
        return result;
    }
}
