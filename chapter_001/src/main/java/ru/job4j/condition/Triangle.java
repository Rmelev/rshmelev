package ru.job4j.condition;
/**
 * square of triangle.
 */
public class Triangle {
    /**
     * point a.
     */
    private Point a;
    /**
     * point b.
     */
    private Point b;
    /**
     * point c.
     */
    private Point c;
    /**
     * Construtor.
     * @param a - coordinates of point a.
     * @param b - coordinates of point b.
     * @param c - coordinates of point c.
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    /**
     *
     * @param left - left-hand point.
     * @param right - right-hand point.
     * @return - distination between two points.
     */
    public double distance(Point left, Point right) {
        int abcissa = right.getX() - left.getX();
        int ordinate = right.getY() - left.getY();
        return Math.sqrt(Math.pow(abcissa, 2) + Math.pow(ordinate, 2));
    }
    /**
     *
     * @param ab - length of segment ab.
     * @param ac - length of segment ac.
     * @param bc - length of segment bc.
     * @return - semiPeriod - half of triangle perimeter.
     */
    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }
    /**
     * @return  - area of triangle.
     */
    public double area() {
        double rsl = -1;
        double ab = this.distance(a, b);
        double bc = this.distance(b, c);
        double ac = this.distance(a, c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            rsl = Math.sqrt(p * (p - ab) * (p - bc) * (p - ac));
        }
        return rsl;
    }
    /**
     * @param ab - length of first side.
     * @param ac - length of second side.
     * @param bc - length of third side.
     * @return - true, if point are not lie on one line.
     */
    private boolean exist(double ab, double ac, double bc) {
        boolean ex = false;
        if ((ab + bc > ac) & (ab + ac > bc) & (ac + bc > ac)) {
            ex = true;
        }
        return ex;
    }
}