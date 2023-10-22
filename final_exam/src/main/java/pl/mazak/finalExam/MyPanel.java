package pl.mazak.finalExam;

import pl.mazak.finalExam.Circle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MyPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    private final JButton addRandomCircle = new JButton("Dodaj losowe koło");
    private final JButton deleteCircles = new JButton("Usuń koła");
    private final JButton sortCircles = new JButton("Sortuj koła");

    private final JButton saveToFile = new JButton("Zapisz koła do pliku");
    private final JButton loadFromFile = new JButton("Wczytaj dane z pliku");
    private LinkedList<Circle> circles = new LinkedList<>();
    private final WidokKolekcji widokKolekcji;
    private int mouseX;
    private int mouseY;


    public MyPanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        addRandomCircle.addActionListener(this);
        deleteCircles.addActionListener(this);
        widokKolekcji = new WidokKolekcji(circles);
        sortCircles.addActionListener(this);
        saveToFile.addActionListener(this);
        loadFromFile.addActionListener(this);
        this.add(widokKolekcji);
        this.add(addRandomCircle);
        this.add(deleteCircles);
        this.add(sortCircles);
        this.add(saveToFile);
        this.add(loadFromFile);
        widokKolekcji.addMouseListener(this);
        widokKolekcji.addMouseMotionListener(this);
        new Thread(new YellowCircleThread()).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == addRandomCircle) {
            String name = getNameDialog();
            if (name == null) return;
            Random random = new Random();
            int randX = random.nextInt(701);
            int randY = random.nextInt(251);
            int randR = random.nextInt(10) + 1;
            Circle circle = new Circle(randX, randY, randR, name);
            circles.add(circle);
        } else if (source == deleteCircles) {
            Iterator<Circle> iterator = circles.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                iterator.remove();
            }
        } else if (source == sortCircles) {
            circles.sort(Comparator.comparing(Circle::getName, String::compareToIgnoreCase));
            System.out.println(circles);
        } else if (source == saveToFile) {
            save();
        } else if (source == loadFromFile) {
            load();
        }
        widokKolekcji.refresh();
    }

    private void load() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("./"));
        jFileChooser.showOpenDialog(this);
        File selectedFile = jFileChooser.getSelectedFile();
        if (selectedFile == null) {
            return;
        }
        try (var ois = new ObjectInputStream(new FileInputStream(selectedFile))) {
            circles = (LinkedList<Circle>) ois.readObject();
            widokKolekcji.setCircles(circles);
            JOptionPane.showMessageDialog(
                    this,
                    "Pomyślnie wczytano plik",
                    "SUKCES",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nie udało się wczytać pliku",
                    "BŁĄD",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void save() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("./"));
        jFileChooser.showSaveDialog(this);
        File selectedFile = jFileChooser.getSelectedFile();
        if (selectedFile == null) {
            return;
        }
        try (var oos = new ObjectOutputStream(new FileOutputStream(selectedFile))) {
            oos.writeObject(circles);
            JOptionPane.showMessageDialog(
                    this,
                    "Pomyślnie zapisano plik",
                    "SUKCES",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nie udało się zapisać pliku",
                    "BŁĄD",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem addCircle = new JMenuItem("Dodaj koło");
        addCircle.addActionListener(event -> {
            String name = getNameDialog();
            if (name == null) return;
            Circle circle = new Circle(mouseX, mouseY, 20, name);
            circles.add(circle);
            widokKolekcji.refresh();
        });
        popupMenu.add(addCircle);
        popupMenu.show(this, mouseX, mouseY);

    }

    private String getNameDialog() {
        String name = JOptionPane.showInputDialog(
                this,
                "Podaj nazwę koła",
                "Dodawanie koła",
                JOptionPane.INFORMATION_MESSAGE
        );
        if (name == null) {
            return null;
        }
        if (name.isBlank()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nazwa nie może być pusta",
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
        return name;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for (Circle circle : circles) {
            circle.move(e.getX() - mouseX, e.getY() - mouseY);
        }
        mouseX = e.getX();
        mouseY = e.getY();
        widokKolekcji.refresh();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public class YellowCircleThread implements Runnable {

        @Override
        public void run() {
            Circle flyingCircle = widokKolekcji.getFlyingCircle();
            while (true) {
                try {
                    flyingCircle.setX((flyingCircle.getX() + 1) % 700);
                    Thread.sleep(10);
                    widokKolekcji.refresh();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
