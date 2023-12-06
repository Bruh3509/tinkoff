package edu.hw9.task2;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public interface ParallelTree {
    List<File> processing();

    List<File> processing(Predicate<File> predicate);
}
