package edu.project4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TransformationsUtility {
    private static final double LEFT_BORDER = -1;
    private static final double RIGHT_BORDER = 1;
    private static final int LINEAR_TRANS = 40;

    public static List<Transformation> getLinearTrans() {
        Random random = new Random();
        List<Transformation> result = new ArrayList<>();

        while (result.size() < LINEAR_TRANS) {
            var a = random.nextDouble(LEFT_BORDER, RIGHT_BORDER);
            var b = random.nextDouble(LEFT_BORDER, RIGHT_BORDER);
            var d = random.nextDouble(LEFT_BORDER, RIGHT_BORDER);
            var e = random.nextDouble(LEFT_BORDER, RIGHT_BORDER);

            var c = random.nextDouble(LEFT_BORDER, RIGHT_BORDER);
            var f = random.nextDouble(LEFT_BORDER, RIGHT_BORDER);

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
            p -> new Point(Math.atan(p.y() / p.x()) / Math.PI, Math.sqrt(p.x() * p.x() + p.y() * p.y()) - 1),
            p -> new Point(
                Math.sqrt(p.x() * p.x() + p.y() * p.y())
                    * Math.sin(Math.sqrt(p.x() * p.x() + p.y() + p.y()) * Math.atan(p.y() / p.x())),
                -1 * Math.sqrt(p.x() * p.x() + p.y() * p.y())
                    * Math.cos(Math.sqrt(p.x() * p.x() + p.y() * p.y()) * Math.atan(p.y() / p.x()))
            ),
            p -> new Point(
                (1 / Math.PI) * Math.atan((p.y()) / p.x())
                    * Math.sin(Math.PI * Math.sqrt(p.x() * p.x() + p.y() * p.y())),
                (1 / Math.PI) * Math.atan(p.y() / p.x()) * Math.cos(Math.PI * Math.sqrt(p.x() * p.x() + p.y() * p.y()))
            )
        );
    }

    private TransformationsUtility() {
    }
}
