package pl.mazak.finalExam;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class WidokKolekcji extends JPanel {

    private LinkedList<Circle> circles;
    private Circle flyingCircle = new Circle(0, 125, 25, "Kubik");

    public Circle getFlyingCircle() {
        return flyingCircle;
    }

    public void setFlyingCircle(Circle flyingCircle) {
        this.flyingCircle = flyingCircle;
    }

    public WidokKolekcji(LinkedList<Circle> circles) {
        this.setPreferredSize(new Dimension(700, 250));
        this.circles = circles;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.setColor(Color.BLACK);
        for (Circle circle : circles) {
            circle.paint(g2d);
        }
        g2d.setColor(Color.YELLOW);
        flyingCircle.paint(g2d);

    }

    public void refresh() {
        this.repaint();
    }

    public LinkedList<Circle> getCircles() {
        return circles;
    }

    public void setCircles(LinkedList<Circle> circles) {
        this.circles = circles;
    }
}
