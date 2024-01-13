package edu.project4;

public record Rect(double x1, double y1, double x2, double y2) {
    boolean contains(Point p) {
        return p.x() >= x1 && p.x() <= x2 && p.y() >= y1 && p.y() <= y2;
    }
}
