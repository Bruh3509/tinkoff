package edu.project4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final ImageProcessor gammaCorrection = canvas1 -> {
        double max = 0.;
        double gamma = 1.5;
        var data = canvas1.data();

        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {
                if (data[i][j].hitCount() >= 1) {
                    data[i][j] = new Pixel(
                        data[i][j].r(),
                        data[i][j].g(),
                        data[i][j].b(),
                        data[i][j].hitCount(),
                        Math.log10(data[i][j].hitCount())
                    );
                    if (data[i][j].normal() > max) {
                        max = data[i][j].normal();
                    }
                }
            }
        }

        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {
                data[i][j] = new Pixel(
                    (int) (data[i][j].r() * Math.pow(data[i][j].normal() / max, 1. / gamma)),
                    (int) (data[i][j].g() * Math.pow(data[i][j].normal() / max, 1. / gamma)),
                    (int) (data[i][j].b() * Math.pow(data[i][j].normal() / max, 1. / gamma)),
                    data[i][j].hitCount(),
                    data[i][j].normal() / max
                );
            }
        }
    };

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        var canvas = FractalImage.create(1920, 1080);
        var task = new MultiThread();
        int samples = 1_000_000;
        short iterPerSample = 20;
        long seed = 10;

        var availableThreads = Runtime.getRuntime().availableProcessors();
        var executor = Executors.newFixedThreadPool(availableThreads);
        var linear = TransformationsUtility.getLinearTrans();
        var notLinear = TransformationsUtility.getNotLinearTrans();

        for (int i = 0; i < availableThreads; ++i) {
            executor.execute(() -> task.render(
                canvas,
                new Rect(-1.777, -1, 1.777, 1),
                linear,
                notLinear,
                Math.floorDiv(samples, availableThreads),
                iterPerSample,
                seed
            ));
        }

        executor.close();

        gammaCorrection.accept(canvas);

        var utils = new ImageUtils();

        utils.save(canvas, Path.of(".", "src", "main", "resources",
            String.format("img%d.png", ThreadLocalRandom.current().nextInt(0, 10000))
        ), ImageFormat.PNG);
    }
}
