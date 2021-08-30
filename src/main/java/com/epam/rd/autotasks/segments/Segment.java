package com.epam.rd.autotasks.segments;

import static java.lang.Math.*;
import static java.lang.StrictMath.pow;

class Segment {

    private Point start;
    private Point end;

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public Segment(Point start, Point end) {

        if (start == null) throw new RuntimeException("start == null");
        if (end == null) throw new RuntimeException("end == null");
        if (start.getX() == end.getX() && start.getY() == end.getY()) throw new RuntimeException("start == end");
        this.start = start;
        this.end = end;
    }

    double length() {
        return sqrt(pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }

    Point middle() {
        double x = (start.getX() + end.getX()) / 2;
        double y = (start.getY() + end.getY()) / 2;
        return new Point(x, y);
    }

    Point intersection(Segment another) {

        double a, b, c, d, x, y;

        // y = ax + b
        a = getA(start, end);
        b = getB(start, end);

        // y = cx + d
        c = getA(another.getStart(), another.getEnd());
        d = getB(another.getStart(), another.getEnd());

        if (a == c) // && b != d
            return null;

        x = (d - b) / (a - c);
        y = a * x + b;

        if ((start.getX() - x) * (end.getX() - x) > 0) return null;
        if ((another.start.getX() - x) * (another.end.getX() - x) > 0) return null;

        return new Point(x, y);

    }

    private double getA(Point start, Point end) {
        return (end.getY() - start.getY()) / (end.getX() - start.getX());
    }

    private double getB(Point start, Point end) {
        return start.getY() - (end.getY() - start.getY()) / (end.getX() / start.getX() - 1);
    }

}
