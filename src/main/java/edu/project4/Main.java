package edu.project4;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        var canvas = FractalImage.create(1920, 1080);

        var result = new SingleThread().render(
            canvas,
            new Rect(-1.777, -1, 1.777, 1),
            TransformationsUtility.getLinearTrans(),
            1_000_000,
            (short) 20,
            10
        );

        ImageProcessor gammaCorrection = canvas1 -> {
            double max = 0.;
            double gamma = 2.8;
            var data = canvas1.data();

            for (int i = 0; i < data.length; ++i) {
                for (int j = 0; j < data[i].length; ++j) {
                    if (data[i][j].hitCount() >= 1) {
                        data[i][j] = new Pixel(data[i][j].r(),
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

        gammaCorrection.accept(result);
        var utils = new ImageUtils();
        utils.save(result, Path.of(".", "src", "main", "resources", "img1.png"), ImageFormat.PNG);
    }
}
