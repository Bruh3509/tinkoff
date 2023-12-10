package edu.project4;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    private static final int SAMPLES = 5_000_000;
    private static final short ITER_PER_SAMPLE = 20;
    private static final int RAND_BOUND = 10000;
    private static final double RECT_LEFT = -1.777;
    private static final double RECT_RIGHT = 1.777;
    private static final double RECT_BOT = -1;
    private static final double RECT_TOP = 1;

    @SuppressWarnings("MagicNumber")
    private static final ImageProcessor GAMMA_CORRECTION = canvas1 -> {
        double max = 0.;
        double gamma = 1.2;
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
        var canvas = FractalImage.create(WIDTH, HEIGHT);
        var task = new MultiThread();

        var availableThreads = Runtime.getRuntime().availableProcessors();
        var executor = Executors.newFixedThreadPool(availableThreads);
        var linear = TransformationsUtility.getLinearTrans();
        var notLinear = TransformationsUtility.getNotLinearTrans();

        for (int i = 0; i < availableThreads; ++i) {
            executor.execute(() -> task.render(
                canvas,
                new Rect(RECT_LEFT, RECT_BOT, RECT_RIGHT, RECT_TOP),
                linear,
                notLinear,
                Math.floorDiv(SAMPLES, availableThreads),
                ITER_PER_SAMPLE,
                0
            ));
        }

        executor.close();

        GAMMA_CORRECTION.accept(canvas);

        var utils = new ImageUtils();

        utils.save(canvas, Path.of(".", "src", "main", "resources",
            String.format("img%d.png", ThreadLocalRandom.current().nextInt(0, RAND_BOUND))
        ), ImageFormat.PNG);
    }

    private Main() {
    }
}
