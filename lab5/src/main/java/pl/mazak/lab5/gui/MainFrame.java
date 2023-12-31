/*
 *  Laboratorium 5
 *
 *   Autor: Michal Maziarz, 263 913
 *    Data: grudzień 2022 r.
 */
package pl.mazak.lab5.gui;

import pl.mazak.lab5.simulation.NarrowBridgeSimulation;
import pl.mazak.lab5.simulation.SimulationTypes;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

public class MainFrame extends JFrame implements ChangeListener, PropertyChangeListener, ActionListener {
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 825;
    private final JLabel drivingLimitationsLabel = new JLabel("Ograniczenie ruchu: ");
    private final JLabel drivingIntensityLabel = new JLabel("Natężenie ruchu: ");

    private final JLabel eastLabel = new JLabel("Wschód");
    private final JLabel westLabel = new JLabel("Zachód");

    private final JSlider sideProportionSlider = new JSlider();
    private final JLabel atBridgeLabel = new JLabel("Na moście: ");
    private final JLabel queueLabel = new JLabel("    Kolejka: ");
    private final JComboBox<?> drivingLimitationsComboBox;
    private final JSlider drivingIntensitySlider = new JSlider();
    private final JTextField atBridgeTextField = new JTextField();
    private final JTextField queueTextField = new JTextField();
    private final JScrollPane logPanel = new JScrollPane();
    private final JTextArea logTextArea;
    private final JButton stopSaveAndExitButton = new JButton("Zatrzymaj symulację");


    private final NarrowBridgeSimulation narrowBridgeSimulation;
    private boolean clickedButton = false;

    private final JMenuBar jMenuBar = new JMenuBar();
        private final JMenu optionsMenuBar = new JMenu("Opcje");
            private final JMenuItem aboutMenuItem = new JMenuItem("O prorgamie");
            private final JMenuItem quitMenuItem = new JMenuItem("Wyjdź");

    public MainFrame(JTextArea logs, NarrowBridgeSimulation narrowBridgeSimulation) {
        this.logTextArea = logs;
        this.narrowBridgeSimulation = narrowBridgeSimulation;
        narrowBridgeSimulation.addPropertyChangeListener(this);
        Font labelFont = new Font(Font.SERIF, Font.PLAIN, 18);
        Font textFieldFont = new Font(Font.MONOSPACED, Font.BOLD, 18);
        this.setTitle("Symulacja przejazdu przez wąski most");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        drivingLimitationsLabel.setFont(labelFont);
        this.add(drivingLimitationsLabel);

        drivingLimitationsComboBox = new JComboBox<>(SimulationTypes.values());
        drivingLimitationsComboBox.setFont(labelFont);
        drivingLimitationsComboBox.setPreferredSize(new Dimension(300, 20));
        drivingLimitationsComboBox.addActionListener(this);
        this.add(drivingLimitationsComboBox);

        drivingIntensityLabel.setFont(labelFont);
        this.add(drivingIntensityLabel);

        drivingIntensitySlider.setPaintLabels(true);
        drivingIntensitySlider.setPreferredSize(new Dimension(370, 50));
        drivingIntensitySlider.setMaximum(4000);
        drivingIntensitySlider.setMajorTickSpacing(2000);
        drivingIntensitySlider.setPaintTicks(true);
        drivingIntensitySlider.setValue(0);
        Hashtable<Integer, JLabel> sliderLabels = new Hashtable<>();
        sliderLabels.put(0, new JLabel("Małe(5s)"));
        sliderLabels.put(2000, new JLabel("Średnie(3s)"));
        sliderLabels.put(4000, new JLabel("Duże(1s)"));
        drivingIntensitySlider.setLabelTable(sliderLabels);
        drivingIntensitySlider.addChangeListener(this);
        this.add(drivingIntensitySlider);

        sideProportionSlider.setMinimum(0);
        sideProportionSlider.setMaximum(100);
        sideProportionSlider.setValue(50);
        sideProportionSlider.setPaintLabels(true);
        sideProportionSlider.setPaintTicks(true);
        sideProportionSlider.setMajorTickSpacing(50);
        Hashtable<Integer, JLabel> proportionLabels = new Hashtable<>();
        proportionLabels.put(0, eastLabel);
        proportionLabels.put(100, westLabel);
        proportionLabels.put(50, new JLabel("1:1"));
        sideProportionSlider.setLabelTable(proportionLabels);
        sideProportionSlider.addChangeListener(this);
        sideProportionSlider.setPreferredSize(new Dimension(500, 50));
        this.add(sideProportionSlider);

        atBridgeLabel.setFont(labelFont);
        this.add(atBridgeLabel);

        atBridgeTextField.setPreferredSize(new Dimension(430, 28));
        atBridgeTextField.setEditable(false);
        atBridgeTextField.setFont(textFieldFont);
        atBridgeTextField.setBackground(Color.WHITE);
        this.add(atBridgeTextField);

        queueLabel.setFont(labelFont);
        this.add(queueLabel);

        queueTextField.setPreferredSize(new Dimension(430, 28));
        queueTextField.setFont(textFieldFont);
        queueTextField.setEditable(false);
        queueTextField.setBackground(Color.WHITE);
        this.add(queueTextField);

        logPanel.setPreferredSize(new Dimension(550, 500));
        logPanel.setViewportView(logTextArea);
        logTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        logTextArea.setEditable(false);
        logTextArea.setBackground(Color.WHITE);
        this.add(logPanel);

        stopSaveAndExitButton.setPreferredSize(new Dimension(500, 45));
        stopSaveAndExitButton.addActionListener(this);
        this.add(stopSaveAndExitButton);

        aboutMenuItem.addActionListener(this);
        quitMenuItem.addActionListener(this);
        optionsMenuBar.add(aboutMenuItem);
        optionsMenuBar.add(quitMenuItem);
        jMenuBar.add(optionsMenuBar);
        this.setJMenuBar(jMenuBar);

        this.setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == drivingIntensitySlider) {
            narrowBridgeSimulation.setPauseDelay(drivingIntensitySlider.getValue());
        } else if (e.getSource() == sideProportionSlider) {
            narrowBridgeSimulation.setWestProbability(sideProportionSlider.getValue());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SwingUtilities.invokeLater(() -> {
            String bussesOnBridgeMessage = narrowBridgeSimulation.getBussesOnBridgeMessage();
            String waitingBusesStringMessage = narrowBridgeSimulation.getWaitingBusesStringMessage();
            atBridgeTextField.setText(bussesOnBridgeMessage);
            queueTextField.setText(waitingBusesStringMessage);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == drivingLimitationsComboBox) {
            SimulationTypes option = (SimulationTypes) drivingLimitationsComboBox.getSelectedItem();
            new Thread(() -> {
                try {
                    narrowBridgeSimulation.changeRules(option);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }).start();
        } else if (e.getSource() == stopSaveAndExitButton) {
            if (!clickedButton) {
                narrowBridgeSimulation.setStarted(false);
                stopSaveAndExitButton.setText("Zapisz logi do pliku tekstowego i wyjdź");
                narrowBridgeSimulation.removePropertyChangeListener(this);
                clickedButton = true;
            } else {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setCurrentDirectory(new File("./"));
                jFileChooser.showSaveDialog(this);
                File selectedFile = jFileChooser.getSelectedFile();
                if (selectedFile == null) {
                    return;
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                    writer.write(logTextArea.getText());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Wystąpił błąd w zapisie do pliku. Spróbuj ponownie",
                            "BŁĄD",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
                JOptionPane.showMessageDialog(this,
                        "Pomyślnie zapisano logi. Program zostanie zamknięty",
                        "Sukces",
                        JOptionPane.INFORMATION_MESSAGE
                );
                System.exit(0);
            }
        } else if (e.getSource() == quitMenuItem) {
            System.exit(0);
        } else if (e.getSource() == aboutMenuItem) {
            JOptionPane.showMessageDialog(this,
                    "Prosta symulacja przejazdu samochodów przez most przy zadanych zasadach ruchu\n\n" +
                            "Autor: Michał Maziarz, 263913",
                    "O programie",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
