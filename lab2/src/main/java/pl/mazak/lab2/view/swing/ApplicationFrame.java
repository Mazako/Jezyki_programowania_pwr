/*
 * Program: Główne okno naszej aplikacji okienkowej wraz z ActionListenerami
 *    Plik: ApplicationFrame.java
 *
 *   Autor: Michal Maziarz, 263913
 *    Data: pazdziernik 2022 r.
 */

package pl.mazak.lab2.view.swing;

import pl.mazak.lab2.model.Animal;
import pl.mazak.lab2.model.AnimalException;
import pl.mazak.lab2.model.AnimalList;
import pl.mazak.lab2.model.Species;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Comparator;
import java.util.stream.Stream;

class ApplicationFrame extends JFrame implements ActionListener {
    //CONSTANT FIELDS
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int TABLE_WIDTH = 600;
    private static final int TABLE_HEIGHT = 450;
    private static final int BUTTON_PANEL_WIDTH = 800;
    private static final int BUTTON_PANEL_HEIGHT = 150;
    private static final String ABOUT_MESSAGE = "Aplikacja do zarzadzania zwierzetami (LAB 2)\n" +
            "Autor: Michal Maziarz, 263 913\n" +
            "grupa: wtorek TN 15:15 ";
    private static final String ABOUT_TITLE = "O aplikacji";
    private static final String WINDOW_TITLE = "Zarządzanie zwierzętami";
    private final JPanel mainPanel = new JPanel();
    private final AnimalsTable animalsTable;
    private final JPanel buttonPanel = new JPanel();
    private final JButton addButton = new JButton("Dodaj nowe zwierzę");
    private final JButton editButton = new JButton("Edytuj zwierzę");
    //button fields
    private final JButton deleteButton = new JButton("Usuń zwierzę");
    private final JButton loadTxtButton = new JButton("Wczytaj zwierzę z pliku");
    private final JButton saveTxtButton = new JButton("Zapisz zwierzę do pliku");
    private final JButton saveButton = new JButton("Zapisz zwierzę do pliku binarnego");
    private final JButton loadButton = new JButton("Wczytaj zwierzę z pliku binarnego");
    private final JButton sortByNameButton = new JButton("Sortuj po imieniu");
    private final JButton sortByTypeButton = new JButton("Sortuj po typie");
    private final JButton sortByAgeButton = new JButton("Sortuj po wieku");
    private final JButton sortByWeightButton = new JButton("Sortuj po masie");
    private final JButton loadCollectionButton = new JButton("Wczytaj tabelę z pliku");
    private final JButton saveCollectionButton = new JButton("Zapisz tabelę do pliku");
    private final JButton aboutButton = new JButton("O programie");
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu ioMenu = new JMenu("Operacje wyjscia/ wejscia");

    //menu bar
    private final JMenu editMenu = new JMenu("Edycja");
    private final JMenu sortMenu = new JMenu("Sortowanie");
    private final JMenu otherMenu = new JMenu("Inne");
    private final JMenuItem addBar = new JMenuItem("Dodaj nowe zwierzę");
    private final JMenuItem editBar = new JMenuItem("Edytuj zwierzę");
    private final JMenuItem deleteBar = new JMenuItem("Usuń zwierzę");
    private final JMenuItem loadTxtBar = new JMenuItem("Wczytaj zwierzę z pliku");
    private final JMenuItem saveTxtBar = new JMenuItem("Zapisz zwierzę do pliku");
    private final JMenuItem saveBar = new JMenuItem("Zapisz zwierzę do pliku binarnego");
    private final JMenuItem loadBar = new JMenuItem("Wczytaj zwierzę z pliku binarnego");
    private final JMenuItem sortByNameBar = new JMenuItem("Sortuj po imieniu");
    private final JMenuItem sortByTypeBar = new JMenuItem("Sortuj po typie");
    private final JMenuItem sortByAgeBar = new JMenuItem("Sortuj po wieku");
    private final JMenuItem sortByWeightBar = new JMenuItem("Sortuj po masie");
    private final JMenuItem loadCollectionBar = new JMenuItem("Wczytaj tabelę z pliku");
    private final JMenuItem saveCollectionBar = new JMenuItem("Zapisz tabelę do pliku");
    private final JMenuItem aboutBar = new JMenuItem("O programie");
    private final JMenuItem exitBar = new JMenuItem("Wyjśie");
    private File previousFile;
    private AnimalList animals = new AnimalList();


    public ApplicationFrame() {
        animals.add(new Animal("Szarik", "Pies", 12, 2.3, Species.MAMMAL));
        animals.add(new Animal("Felek", "Kot", 2, 0.5, Species.MAMMAL));
        animalsTable = new AnimalsTable(animals);
        animalsTable.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        initMenuPart();
        initButtonPart();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setResizable(false);
        this.setTitle(WINDOW_TITLE);
        this.setLocationRelativeTo(null);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(animalsTable);
        mainPanel.add(buttonPanel);
        this.add(mainPanel);
        this.setVisible(true);
    }

    private void initMenuPart() {
        editMenu.add(addBar);
        editMenu.add(editBar);
        editMenu.add(deleteBar);

        ioMenu.add(loadTxtBar);
        ioMenu.add(saveTxtBar);
        ioMenu.addSeparator();
        ioMenu.add(loadBar);
        ioMenu.add(saveBar);
        ioMenu.addSeparator();
        ioMenu.add(loadCollectionBar);
        ioMenu.add(saveCollectionBar);

        sortMenu.add(sortByNameBar);
        sortMenu.add(sortByAgeBar);
        sortMenu.add(sortByTypeBar);
        sortMenu.add(sortByWeightBar);

        otherMenu.add(aboutBar);
        otherMenu.add(exitBar);

        menuBar.add(editMenu);
        menuBar.add(ioMenu);
        menuBar.add(sortMenu);
        menuBar.add(otherMenu);

        Stream.of(
                addBar,
                editBar,
                deleteBar,
                loadTxtBar,
                saveTxtBar,
                saveBar,
                loadBar,
                sortByNameBar,
                sortByTypeBar,
                sortByAgeBar,
                sortByWeightBar,
                loadCollectionBar,
                saveCollectionBar,
                aboutBar,
                exitBar
        ).forEach(bar -> bar.addActionListener(this));
        this.setJMenuBar(menuBar);
    }

    private void initButtonPart() {
        buttonPanel.setPreferredSize(new Dimension(BUTTON_PANEL_WIDTH, BUTTON_PANEL_HEIGHT));
        buttonPanel.setLayout(new FlowLayout());
        Stream.of(addButton,
                editButton,
                deleteButton,
                saveButton,
                loadButton,
                saveTxtButton,
                loadTxtButton,
                loadCollectionButton,
                saveCollectionButton,
                sortByAgeButton,
                sortByNameButton,
                sortByTypeButton,
                sortByWeightButton,
                aboutButton
        ).forEach(x -> {
            x.addActionListener(this);
            buttonPanel.add(x);
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        try {
            if (source == aboutButton || source == aboutBar) {
                showAboutDialog();
            } else if (source == addButton || source == addBar) {
                addNewAnimal();
            } else if (source == editButton || source == editBar) {
                editAnimal();
            } else if (source == deleteButton || source == deleteBar) {
                deleteAnimal();
            } else if (source == loadTxtButton || source == loadTxtBar) {
                loadFromTxt();
            } else if (source == saveTxtButton || source == saveTxtBar) {
                saveToTxt();
            } else if (source == loadButton || source == loadBar) {
                loadFromBinary();
            } else if (source == saveButton || source == saveBar) {
                saveToBinary();
            } else if (source == saveCollectionButton || source == saveCollectionBar) {
                saveCollectionToBinary();
            } else if (source == loadCollectionButton || source == loadCollectionBar) {
                loadCollectionFromBinary();
            } else if (source == sortByAgeButton || source == sortByAgeBar) {
                animals.sort(Comparator.comparingInt(Animal::getAge));
            } else if (source == sortByNameButton || source == sortByNameBar) {
                animals.sort(Comparator.comparing(Animal::getName, String.CASE_INSENSITIVE_ORDER));
            } else if (source == sortByTypeButton || source == sortByTypeBar) {
                animals.sort(Comparator.comparing(Animal::getType, String.CASE_INSENSITIVE_ORDER));
            } else if (source == sortByWeightButton || source == sortByWeightBar) {
                animals.sort(Comparator.comparingDouble(Animal::getWeight));
            } else if (source == exitBar) {
                this.dispose();
            }
        } catch (AnimalException exception) {
            JOptionPane.showMessageDialog(this,
                    exception.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
        animalsTable.refreshView(animals);
    }

    private void loadCollectionFromBinary() throws AnimalException {
        JFileChooser jFileChooser = getJFileChooser();
        jFileChooser.showOpenDialog(this);
        File selectedFile = jFileChooser.getSelectedFile();
        if (selectedFile == null) {
            return;
        }
        String absolutePath = selectedFile.getAbsolutePath();
        animals = AnimalList.readFromBinary(absolutePath);
        showMessageDialog("Wczytano", "Pomyslnie wczytano tabele");
        setPreviousFile(selectedFile);
    }

    private void saveCollectionToBinary() throws AnimalException {
        if (animals.isEmpty()) {
            throw new AnimalException("Nie mozna zapisac pustej tabeli");
        }
        JFileChooser jFileChooser = getJFileChooser();
        jFileChooser.showSaveDialog(this);
        File selectedFile = jFileChooser.getSelectedFile();
        if (selectedFile == null) {
            return;
        }
        String absolutePath = selectedFile.getAbsolutePath();
        AnimalList.writeToBinary(absolutePath, animals);
        showMessageDialog("Zapisano",
                "Pomyslnie zapisano tabele do sciezki\n" +
                        absolutePath
        );
        setPreviousFile(selectedFile);

    }

    private void saveToBinary() throws AnimalException {
        int selectedRow = animalsTable.getSelectedRow();
        if (selectedRow == -1) {
            throw new AnimalException("Nie wybrano zadnego zwierzecia");
        }
        Animal animal = animals.get(selectedRow);
        JFileChooser jFileChooser = getJFileChooser();
        jFileChooser.showSaveDialog(this);
        File selectedFile = jFileChooser.getSelectedFile();
        if (selectedFile == null) {
            return;
        }
        String absolutePath = selectedFile.getAbsolutePath();
        Animal.writeAnimalToBinary(absolutePath, animal);
        showMessageDialog("Zapisano",
                "Pomyslnie zapisano plik do sciezki\n" +
                        absolutePath
        );
        setPreviousFile(selectedFile);

    }

    private JFileChooser getJFileChooser() {
        JFileChooser jFileChooser = new JFileChooser();
        if (previousFile != null) {
            jFileChooser.setCurrentDirectory(previousFile);
        }
        return jFileChooser;
    }

    private void loadFromBinary() throws AnimalException {
        JFileChooser jFileChooser = getJFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
        jFileChooser.showOpenDialog(this);
        File selectedFile = jFileChooser.getSelectedFile();
        if (selectedFile == null) {
            return;
        }
        String absolutePath = selectedFile.getAbsolutePath();
        Animal animal = Animal.readAnimalFromBinary(absolutePath);
        animals.add(animal);
        showMessageDialog("Wczytano", "Pomyslnie wczytano plik");
        setPreviousFile(selectedFile);
    }

    private void saveToTxt() throws AnimalException {
        int selectedRow = animalsTable.getSelectedRow();
        if (selectedRow == -1) {
            throw new AnimalException("Nie wybrano zadnego zwierzecia");
        }
        Animal animal = animals.get(selectedRow);
        JFileChooser jFileChooser = getJFileChooser();
        jFileChooser.showSaveDialog(this);
        File selectedFile = jFileChooser.getSelectedFile();
        if (selectedFile == null) {
            return;
        }
        String absolutePath = selectedFile.getAbsolutePath();
        Animal.writeAnimalToFile(absolutePath, animal);
        showMessageDialog("Zapisano",
                "Pomyslnie zapisano plik do sciezki\n" +
                        absolutePath
        );
        setPreviousFile(selectedFile);
    }

    private void loadFromTxt() throws AnimalException {
        JFileChooser jFileChooser = getJFileChooser();
        jFileChooser.setFileFilter(new FileNameExtensionFilter("CSV_OR_TEXT_FILES", "txt", "text", "csv"));
        jFileChooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
        jFileChooser.showOpenDialog(this);
        File selectedFile = jFileChooser.getSelectedFile();
        if (selectedFile == null) {
            return;
        }
        String absolutePath = selectedFile.getAbsolutePath();
        Animal animal = Animal.readAnimalFromFile(absolutePath);
        animals.add(animal);
        showMessageDialog("Wczytano", "Pomyslnie wczytano plik");
        setPreviousFile(selectedFile);
    }

    private void deleteAnimal() throws AnimalException {
        int selectedRow = animalsTable.getSelectedRow();
        if (selectedRow == -1) {
            throw new AnimalException("Nie wybrano zadnego zwierzecia");
        }
        animals.remove(selectedRow);
    }

    private void editAnimal() throws AnimalException {
        int selectedRow = animalsTable.getSelectedRow();
        if (selectedRow == -1) {
            throw new AnimalException("Nie wybrano zadnego zwierzecia");
        }
        Animal animal = animals.get(selectedRow);
        AddAnimalDialog dialog = new AddAnimalDialog(this, animal);
        Animal editedAnimal = dialog.getAnimal();
        if (!(editedAnimal == null)) {
            animals.replace(selectedRow, editedAnimal);
        }

    }

    private void addNewAnimal() {
        AddAnimalDialog animalDialog = new AddAnimalDialog(this);
        Animal animal = animalDialog.getAnimal();
        if (!(animal == null)) {
            animals.add(animal);
        }
    }

    private void showMessageDialog(String title, String message) {
        JOptionPane.showMessageDialog(this,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void showAboutDialog() {
        showMessageDialog(ABOUT_TITLE, ABOUT_MESSAGE);
    }

    private void setPreviousFile(File file) {
        this.previousFile = file.getParentFile();

    }
}
