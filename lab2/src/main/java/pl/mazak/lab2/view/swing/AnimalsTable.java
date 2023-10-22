/*
 * Program: Okno tabeli zwierzat
 *    Plik: AnimalsTable.java
 *
 *   Autor: Michal Maziarz, 263913
 *    Data: pazdziernik 2022 r.
 */

package pl.mazak.lab2.view.swing;

import pl.mazak.lab2.model.AnimalList;

import javax.swing.*;
import java.awt.*;

class AnimalsTable extends JScrollPane {

    private final JTable table;
    private AnimalsTableModel model;

    public AnimalsTable(AnimalList animalList) {
        model = new AnimalsTableModel(animalList.getAnimals());
        table = new JTable(model);
        table.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
        table.setRowHeight(table.getRowHeight() + 16);
        table.setFillsViewportHeight(true);
        table.setColumnSelectionAllowed(false);
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);
        this.setViewportView(table);
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    public void refreshView(AnimalList animalList) {

        model = new AnimalsTableModel(animalList.getAnimals());
        table.setModel(model);
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }
}
