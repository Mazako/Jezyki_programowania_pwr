package pl.mazak.finalExam;

import java.awt.*;
import java.io.Serializable;

public class Circle implements Serializable {
    public static long serialVersionUID = 1L;
    private int x;
    private int y;
    private int r;
    private String name;

    public Circle(int x, int y, int r, String name) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.name = name;
    }

    public void paint(Graphics2D g2d) {
        g2d.fillOval(x - r, y - r, 2 * r, 2 * r);
        g2d.drawString(name, x - r, y - r);
    }

    public void move(int dx, int dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public Circle(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
