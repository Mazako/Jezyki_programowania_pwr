/*
 * Program: Klasa główna do odpalenia aplikacji okienkowej
 *    Plik: Main.java
 *
 *   Autor: Michal Maziarz, 263913
 *    Data: pazdziernik 2022 r.
 */

package pl.mazak.lab2.view.swing;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ApplicationFrame::new);
    }
}
