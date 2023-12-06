package edu.hw9.task2;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class ParallelTree1000 extends RecursiveTask<List<Path>> {
    private final Path root;
    private final int minFiles;
    private int curDirFiles;

    public ParallelTree1000(Path root) {
        this.root = root;
        minFiles = 1000;
        curDirFiles = 0;
    }

    public ParallelTree1000(Path root, int minFiles) {
        this.root = root;
        this.minFiles = minFiles;
        curDirFiles = 0;
    }

    @Override
    protected List<Path> compute() { //TODO
        var tasks = createSubtasks();
        if (tasks.isEmpty()) {
            if (curDirFiles > minFiles) {
                System.out.printf("path - %s, %d\n", root, curDirFiles);
            }
            return List.of();
        } else {
            return ForkJoinTask.invokeAll(tasks)
                .stream()
                .map(obj -> obj.root)
                .toList();
        }
    }

    private Collection<ParallelTree1000> createSubtasks() {
        List<ParallelTree1000> result = new ArrayList<>();
        try (var dir = Files.newDirectoryStream(root)) {
            dir.forEach(in -> {
                if (Files.isDirectory(in)) {
                    result.add(new ParallelTree1000(in, minFiles));
                } else {
                    ++curDirFiles;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /*
    private List<Path> processing() {
        try (var dir = Files.newDirectoryStream(root)) {
            dir.forEach(in -> {
                if (Files.isRegularFile(in)) {
                    ++curDirFiles;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return List.of(root);
    }
     */
}
