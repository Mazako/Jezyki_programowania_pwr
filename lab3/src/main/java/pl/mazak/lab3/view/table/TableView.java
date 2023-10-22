/*
 * Program: Klasa reprezentująca JScrollPane naszej tabeli
 *
 *    Plik: TableView.java
 *
 *   Autor: Michal Maziarz, 263913
 *    Data: listopad 2022 r.
 */
package pl.mazak.lab3.view.table;

import pl.mazak.lab3.model.Animal;
import pl.mazak.lab3.model.AnimalException;
import pl.mazak.lab3.model.Species;
import pl.mazak.lab3.model.collection.GroupOfAnimals;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TableView extends JScrollPane {
    private JTable table;
    private TableModel tableModel;

    public TableView(List<GroupOfAnimals> groupOfAnimalsList, int width, int height) {
        this(width, height);
        tableModel = new TableModel(groupOfAnimalsList);
        table = new JTable(tableModel);
        this.setViewportView(table);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
    }

    private TableView(int width, int height) {
        this.setMaximumSize(new Dimension(width, height));
    }

    public TableView(GroupOfAnimals groupOfAnimals, int width, int height) {
        this(width, height);
        tableModel = new TableModel(groupOfAnimals);
        table = new JTable(tableModel);
        this.setViewportView(table);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
    }



    public int getSelectedRow() throws AnimalException {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            throw new AnimalException("Nie wybrano żadnego rzędu");
        }
        return selectedRow;
    }

    public void refreshView(List<GroupOfAnimals> groupOfAnimalsList) {
        tableModel = new TableModel(groupOfAnimalsList);
        table.setModel(tableModel);
    }

    public void refreshView(GroupOfAnimals groupOfAnimals) {
        tableModel = new TableModel(groupOfAnimals);
        table.setModel(tableModel);
    }

    public Animal getAnimalFromTable(int index) {
        String name = (String) tableModel.getValueAt(index, 0);
        String type = (String) tableModel.getValueAt(index, 1);
        int age = (int) tableModel.getValueAt(index, 2);
        double weight = (double) tableModel.getValueAt(index, 3);
        Species species = (Species) tableModel.getValueAt(index, 4);
        return new Animal(name, type, age, weight, species);
    }
}

