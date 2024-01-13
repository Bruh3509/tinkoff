package edu.project4;

import java.util.List;

@FunctionalInterface
public interface Renderer {
    void render(
        FractalImage canvas,
        Rect world,
        List<Transformation> linear,
        List<Transformation> notLinear,
        int samples,
        short iterPerSample,
        long seed
    );
}
