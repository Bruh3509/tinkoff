package edu.project4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TransformationsUtility {
    private static final double leftBorder = -1.5;
    private static final double rightBorder = 1.5;

    public static List<Transformation> getLinearTrans() {
        Random random = new Random();
        List<Transformation> result = new ArrayList<>();

        while (result.size() < 10) {
            var a = random.nextDouble(leftBorder, rightBorder);
            var b = random.nextDouble(leftBorder, rightBorder);
            var d = random.nextDouble(leftBorder, rightBorder);
            var e = random.nextDouble(leftBorder, rightBorder);

            var c = random.nextDouble(leftBorder, rightBorder);
            var f = random.nextDouble(leftBorder, rightBorder);

            if (a * a + d * d < 1
                && b * b + e * e < 1
                && a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d)) {
                result.add(p -> new Point(
                    a * p.x() + b * p.y() + c,
                    d * p.x() + e * p.y() + f
                ));
            }
        }

        return result;
    }

    public static List<Transformation> getNotLinearTrans() {
        return List.of(
            p -> new Point(Math.sin(p.x()), Math.sin(p.y())),
            p -> new Point(p.x() / (p.x() * p.x() + p.y() * p.y()), p.y() / (p.x() * p.x() + p.y() * p.y())),
            p -> new Point(Math.atan(p.y() / p.x()) / Math.PI, Math.sqrt(p.x() * p.x() + p.y() * p.y()) - 1)
        );
    }

    private TransformationsUtility() {
    }
}
