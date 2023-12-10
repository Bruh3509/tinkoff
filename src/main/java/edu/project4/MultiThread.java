package edu.project4;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThread implements Renderer {
    private static final int SYMMETRY = 20;
    private static final int COLOR_CONST = 256;
    private static final short NOT_PAINT_TIME = -20;
    private final Lock lock = new ReentrantLock(true);
    private final ConcurrentHashMap<Transformation, Color> initColorToTrans = new ConcurrentHashMap<>();

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

        for (int num = 0; num < samples; ++num) {
            Point startPoint = new Point(
                ThreadLocalRandom.current().nextDouble(world.x1(), world.x2()),
                ThreadLocalRandom.current().nextDouble(world.y1(), world.y2())
            );

            var randTransform = linear.get(ThreadLocalRandom.current().nextInt(0, linear.size()));
            var randNotLinear = notLinear.get(ThreadLocalRandom.current().nextInt(0, notLinear.size()));

            initColorToTrans.putIfAbsent(
                randTransform,
                new Color(
                    ThreadLocalRandom.current().nextInt(0, COLOR_CONST),
                    ThreadLocalRandom.current().nextInt(0, COLOR_CONST),
                    ThreadLocalRandom.current().nextInt(0, COLOR_CONST)
                )
            );
            for (short step = NOT_PAINT_TIME; step < iterPerSample; ++step) {

                var newPoint = randNotLinear.apply(randTransform.apply(startPoint));
                startPoint = newPoint;

                if (step >= 0 && world.contains(newPoint)) {
                    double theta = 0.0;
                    for (int s = 0; s < SYMMETRY; theta += Math.PI * 2 / s, ++s) {
                        var rotX = newPoint.x() * Math.cos(theta) - newPoint.y() * Math.sin(theta);
                        var rotY = newPoint.x() * Math.sin(theta) + newPoint.y() * Math.cos(theta);

                        var x1 =
                            canvas.width() - (int) Math.floor((((world.x2() - rotX) / (world.x2() - world.x1()))
                                * canvas.width()));
                        var y1 =
                            canvas.height() - (int) Math.floor((((world.y2() - rotY) / (world.y2() - world.y1())))
                                * canvas.height());

                        if (canvas.contains(x1, y1)) {
                            var pixel = canvas.pixel(x1, y1);
                            var initColor = initColorToTrans.get(randTransform);
                            synchronized (pixel) {
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
    }
}
