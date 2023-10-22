/*
 * Program: Model danych do tabeli zwierzat
 *    Plik: AnimalsTableModel.java
 *
 *   Autor: Michal Maziarz, 263913
 *    Data: pazdziernik 2022 r.
 */

package pl.mazak.lab2.view.swing;

import pl.mazak.lab2.model.Animal;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

class AnimalsTableModel extends DefaultTableModel {
    private static final String[] COLUMN_HEADERS = {"Imię", "Typ", "Wiek", "Masa [kg]", "Gatunek"};

    public AnimalsTableModel(Vector<Animal> animals) {
        super(COLUMN_HEADERS, 0);
        for (Animal animal : animals) {
            this.addRow(new Object[]{animal.getName(), animal.getType(), animal.getAge(), animal.getWeight(), animal.getSpecies()});
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }


}
