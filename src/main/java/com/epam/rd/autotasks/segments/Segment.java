package com.epam.rd.autotasks.segments;

import java.awt.geom.Line2D;

class Segment {
    Point start;
    Point end;

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
        if (start.equals(end) || end == null) {
            throw new RuntimeException();
        }
    }

    double length() {
        return Math.sqrt(Math.pow((end.getX() - start.getX()), 2) + Math.pow((end.getY() - start.getY()), 2));
    }

    Point middle() {
        double middleX = (start.getX() + end.getX()) / 2;
        double middleY = (start.getY() + end.getY()) / 2;
        return new Point(middleX, middleY);
    }

    Point intersection(Segment another) {
        Line2D line1 = new Line2D.Float((float) start.getX(), (float) start.getY(), (float) end.getX(), (float) end.getY());
        Line2D line2 = new Line2D.Float((float) another.start.getX(), (float) another.start.getY(), (float) another.end.getX(), (float) another.end.getY());
        boolean result = line2.intersectsLine(line1);
        if (!result) {
            return null;
        } else {
            double x = start.getX() - end.getX();
            double y = end.getY() - start.getY();
            double firstPoint = y * (start.getX()) + x * (start.getY());

            double anotherX = another.start.getX() - another.end.getX();
            double anotherY = another.end.getY() - another.start.getY();
            double secondPoint = anotherY * (another.start.getX()) + anotherX * (another.start.getY());

            double det = y * anotherX - anotherY * x;
            double intersectionX = (anotherX * firstPoint - x * secondPoint) / det;
            double intersectionY = (y * secondPoint - anotherY * firstPoint) / det;
            if (det == 0) {
                return null;
            }
            return new Point(intersectionX, intersectionY);
        }
    }
}
