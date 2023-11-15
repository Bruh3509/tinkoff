package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class CopyFile {
    public static String cloneFile(Path path) throws IOException {
        var fileDirectory = path.getParent();
        var fileName = path.getName(path.getNameCount() - 1).toString().split("\\.");
        var name = fileName[0];
        var extension = fileName[1];
        var pattern = Pattern.compile(name + "( - copy( \\((\\d+)\\))?)\\." + extension);

        var copyString = copyCalculation(fileDirectory, pattern);

        var newFilePathName = Path.of(fileDirectory.toString(), name + copyString + "." + extension);
        Files.copy(path, newFilePathName);
        return name + copyString + extension;
    }

    @SuppressWarnings("MagicNumber")
    private static String copyCalculation(Path fileDirectory, Pattern pattern) {
        int maxCopy = 0;
        var copyString = " - copy (1)";

        try (var stream = Files.newDirectoryStream(fileDirectory)) {
            for (var file : stream) {
                var matcher = pattern.matcher(file.getName(file.getNameCount() - 1).toString());
                if (matcher.find() && matcher.group(3) != null) {
                    var copyCount = Integer.parseInt(matcher.group(3));
                    if (copyCount > maxCopy) {
                        maxCopy = copyCount;
                        copyString = " - copy (" + (++copyCount) + ")";
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return copyString;
    }

    private CopyFile() {
    }
}
