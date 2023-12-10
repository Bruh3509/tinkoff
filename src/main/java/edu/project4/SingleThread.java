package edu.project4;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SingleThread implements Renderer {
    @Override
    public void render(
        FractalImage canvas,
        Rect world,
        List<Transformation> linear,
        List<Transformation> notLinear,
        int samples,
        short iterPerSample,
        long seed
    ) {
        Random random = new Random();

        Map<Transformation, Color> initColorToTrans = new HashMap<>();
        for (int num = 0; num < samples; ++num) {
            Point startPoint = new Point(
                random.nextDouble(world.x1(), world.x2()),
                random.nextDouble(world.y1(), world.y2())
            );

            var randTransform = linear.get(random.nextInt(0, linear.size()));
            var randNotLinear = notLinear.get(random.nextInt(0, notLinear.size()));

            initColorToTrans.putIfAbsent(
                randTransform,
                new Color(random.nextInt(0, 256), random.nextInt(0, 256), random.nextInt(0, 256))
            );
            for (short step = -20; step < iterPerSample; ++step) {

                var newPoint = randNotLinear.apply(randTransform.apply(startPoint));
                startPoint = newPoint;

                if (step >= 0 && world.contains(newPoint)) {
                    // count coordinates
                    var x1 =
                        canvas.width() - (int) Math.floor((((world.x2() - newPoint.x()) / (world.x2() - world.x1()))
                            * canvas.width()));
                    var y1 =
                        canvas.height() - (int) Math.floor((((world.y2() - newPoint.y()) / (world.y2() - world.y1())))
                            * canvas.height());

                    if (canvas.contains(x1, y1)) {
                        var pixel = canvas.pixel(x1, y1);
                        var initColor = initColorToTrans.get(randTransform);
                        if (pixel.hitCount() < 1) {
                            canvas.data()[x1][y1] =
                                new Pixel(initColor.getRed(), initColor.getGreen(), initColor.getBlue(), 1, 0);
                        } else {
                            canvas.data()[x1][y1] = new Pixel(
                                (pixel.r() + initColor.getRed()) / 2,
                                (pixel.g() + initColor.getGreen()) / 2,
                                (pixel.b() + initColor.getBlue()) / 2,
                                pixel.hitCount() + 1,
                                0
                            );
                        }
                    }
                }
            }
        }
    }
}
