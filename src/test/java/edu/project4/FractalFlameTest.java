package edu.project4;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FractalFlameTest {
    static final ImageProcessor gammaCorrection = canvas1 -> {
        double max = 0.;
        double gamma = 2.2;
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

    @Test
    @Disabled
    @DisplayName("Saving Test Multiple Render")
    void testPictureSave() throws IOException {
        // arrange
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

        var saveUtil = new ImageUtils();
        Path path = Path.of(".", "src", "main", "resources", "imgMulti.png");

        // act
        saveUtil.save(canvas, path, ImageFormat.PNG);

        // assert
        assertTrue(Files.exists(path));
    }

    @Test
    @Disabled
    @DisplayName("Saving Test Single")
    void testSavingSingle() throws IOException {
        // arrange
        var canvas = FractalImage.create(1920, 1080);
        new SingleThread().render(
            canvas,
            new Rect(-1.777, -1, 1.777, 1),
            TransformationsUtility.getLinearTrans(),
            TransformationsUtility.getNotLinearTrans(),
            1_000_000,
            (short) 20,
            10
        );
        gammaCorrection.accept(canvas);

        var saveUtil = new ImageUtils();
        Path path = Path.of(".", "src", "main", "resources", "imgSingle.png");

        // act
        saveUtil.save(canvas, path, ImageFormat.PNG);

        // assert
        assertTrue(Files.exists(path));
    }

    @Test
    @DisplayName("Test Fractal Image")
    void testFractalImage() {
        // arrange
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

        // act
        var isContains = canvas.contains(0, 0);
        var isContains1 = canvas.contains(1920, 1080);
        var isContains2 = canvas.contains(242, 124);

        var pixel = canvas.pixel(0, 0);
        var pixel1 = canvas.pixel(773, 912);

        // assert

        // contains test
        assertTrue(isContains);
        assertTrue(isContains2);
        assertFalse(isContains1);

        // pixel test
        assertThat(pixel).isNotNull();
        assertThat(pixel1).isNotNull();
    }
}
