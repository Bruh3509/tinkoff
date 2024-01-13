package edu.project4;

public record FractalImage(Pixel[][] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        Pixel[][] result = new Pixel[width][height];

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                result[i][j] = new Pixel(0, 0, 0, 0, 0);
            }
        }

        return new FractalImage(result, width, height);
    }

    boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    Pixel pixel(int x, int y) {
        return data[x][y];
    }
}
