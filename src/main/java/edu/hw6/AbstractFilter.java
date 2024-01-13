package edu.hw6;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

@FunctionalInterface
public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    AbstractFilter REGULAR_FILE = Files::isRegularFile;
    AbstractFilter READABLE = Files::isReadable;
    AbstractFilter WRITABLE = Files::isWritable;

    static AbstractFilter largerThan(int size) {
        return path -> Files.size(path) > size;
    }

    @SuppressWarnings("MagicNumber")
    static AbstractFilter magicNumber(int... numbers) {
        return path -> {
            try (var fileReader = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                byte[] allBytes = fileReader.readAllBytes();
                if (allBytes.length == 0) {
                    return false;
                }
                String zeroByte = Integer.toHexString((int) allBytes[0] & 0xFF);
                for (var num : numbers) {
                    if (String.valueOf(num).equals(zeroByte)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    static AbstractFilter globMatches(String extension) {
        return path -> path.endsWith(extension.substring(1));
    }

    static AbstractFilter regexFilter(String regex) {
        return path -> path.getFileName().toString().matches(regex);
    }

    @Override
    boolean accept(Path entry) throws IOException;

    default AbstractFilter and(AbstractFilter pathAbstractFilter) {
        return (Path t) -> pathAbstractFilter.accept(t) && accept(t);
    }
}
