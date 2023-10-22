package pl.mazak.finalExam;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame implements ActionListener {
    private final MyPanel myPanel = new MyPanel();

    private final JMenuBar jMenu = new JMenuBar();
    private final JMenu menu = new JMenu("Menu głowne");

    private final JMenuItem author = new JMenuItem("Autor");
    private final JMenuItem description = new JMenuItem("Opis programu");
    private final JMenuItem close = new JMenuItem("Zakończ");

    public MyWindow(){
        this.setSize(700, 350);
        this.setLocationRelativeTo(null);
        this.setTitle("Michał Maziarz");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(myPanel);
        author.addActionListener(this);
        description.addActionListener(this);
        close.addActionListener(this);
        menu.add(author);
        menu.add(description);
        menu.add(close);
        jMenu.add(menu);
        this.setJMenuBar(jMenu);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
