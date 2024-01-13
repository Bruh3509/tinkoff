package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class AbstractFilterTest {
    private static final Path CUR_DIR = Paths.get(".");
    private static final Path RESOURCES = Paths.get("src", "main", "resources");

    @Test
    @DisplayName("Abstract Filter Test")
    void testAbstractFilterLargerThan() {
        // arrange
        var filter1 = AbstractFilter.READABLE
            .and(AbstractFilter.REGULAR_FILE)
            .and(AbstractFilter.largerThan(1000));
        ArrayList<String> result1 = new ArrayList<>();

        // act
        try (var entries = Files.newDirectoryStream(CUR_DIR, filter1)) {
            entries.forEach(file -> result1.add(file.getFileName().toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // assert
        assertThat(result1)
            .containsExactlyInAnyOrder(
                "checkstyle.xml",
                ".editorconfig",
                "mvnw.cmd",
                "mvnw",
                "pom.xml"
            );
    }

    @Test
    @DisplayName("Abstract Filter Test Glob Matches")
    void testAbstractFilterGlobMatches() {
        // arrange
        var filter2 = AbstractFilter.REGULAR_FILE
            .and(AbstractFilter.WRITABLE)
            .and(AbstractFilter.globMatches("*.gitignore"));
        ArrayList<String> result2 = new ArrayList<>();

        // act
        try (var entries = Files.newDirectoryStream(CUR_DIR, filter2)) {
            entries.forEach(file -> result2.add(file.getFileName().toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // assert
        assertThat(result2)
            .containsExactlyInAnyOrder(".gitignore");
    }

    @Test
    @DisplayName("Abstact Filter Test Regex Filter")
    void testAbstractFilterRegexFilter() {
        // arrange
        var filter3 = AbstractFilter.REGULAR_FILE
            .and(AbstractFilter.READABLE)
            .and(AbstractFilter.regexFilter("\\.[a-zA-Z]+"));
        ArrayList<String> result3 = new ArrayList<>();

        // act
        try (var entries = Files.newDirectoryStream(CUR_DIR, filter3)) {
            entries.forEach(file -> result3.add(file.getFileName().toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // assert
        assertThat(result3)
            .containsExactlyInAnyOrder(
                ".editorconfig",
                ".gitattributes",
                ".gitignore"
            );
    }

    @Test
    @DisplayName("Abstract Filter Test Magic Number")
    void testAbstractFilterMagicNumber() {
        // arrange
        var filter4 = AbstractFilter.REGULAR_FILE
            .and(AbstractFilter.magicNumber(89));
        ArrayList<String> result4 = new ArrayList<>();

        // act
        try (var entries = Files.newDirectoryStream(RESOURCES, filter4)) {
            entries.forEach(file -> result4.add(file.getFileName().toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // assert
        assertThat(result4)
            .containsExactlyInAnyOrder(
                "0.png",
                "img1067.png",
                "img8634.png",
                "img4017.png",
                "img5465.png",
                "img6487.png",
                "img2458.png",
                "img9354.png",
                "img6400.png",
                "img5487.png",
                "img9196.png",
                "img427.png",
                "img5983.png");
    }
}
