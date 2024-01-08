package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ParallelTree1000 extends RecursiveTask<List<Path>> {
    private final Path root;
    private final int minFiles;
    private int curDirFiles;
    private final List<Path> allPaths;

    @SuppressWarnings("MagicNumber")
    public ParallelTree1000(Path root) {
        this.root = root;
        minFiles = 1000;
        curDirFiles = 0;
        allPaths = new ArrayList<>();
    }

    public ParallelTree1000(Path root, int minFiles) {
        this.root = root;
        this.minFiles = minFiles;
        curDirFiles = 0;
        allPaths = new ArrayList<>();
    }

    private ParallelTree1000(Path root, int minFiles, List<Path> allPaths) {
        this.root = root;
        this.minFiles = minFiles;
        curDirFiles = 0;
        this.allPaths = allPaths;
    }

    @Override
    protected List<Path> compute() { // TODO (Streams)
        var tasks = createSubtasks();
        if (tasks.isEmpty()) {
            if (curDirFiles > minFiles) {
                allPaths.add(root);
            }
        } else {
            ForkJoinTask.invokeAll(tasks);
        }

        return allPaths;
    }

    private Collection<ParallelTree1000> createSubtasks() {
        List<ParallelTree1000> result = new ArrayList<>();
        try (var dir = Files.newDirectoryStream(root)) {
            dir.forEach(in -> {
                if (Files.isDirectory(in) && !in.getFileName().toString().startsWith(".")) {
                    result.add(new ParallelTree1000(in, minFiles, allPaths));
                } else {
                    ++curDirFiles;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
