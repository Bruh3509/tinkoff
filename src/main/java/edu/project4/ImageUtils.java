package edu.project4;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.nio.file.Path;

public class ImageUtils {
    public void save(FractalImage image, Path path, ImageFormat format) throws IOException {
        BufferedImage resImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        var pixels = image.data();

        for (int i = 0; i < pixels.length; ++i) {
            for (int j = 0; j < pixels[i].length; ++j) {
                var cur = pixels[i][j];
                resImage.setRGB(i, j, cur.r() * cur.g() * cur.b());
            }
        }

        ImageIO.write(resImage, format.toString().toLowerCase(), path.toFile());
    }
}
