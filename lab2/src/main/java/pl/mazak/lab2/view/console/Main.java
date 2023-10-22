package pl.mazak.lab2.view.console;/*
 * Program: Klasa startowa naszej aplikacji
 *    Plik: view.console.Main.java
 *
 *   Autor: Michal Maziarz, 263913
 *    Data: pazdziernik 2022 r.
 */

public class Main {
    public static void main(String[] args) {
        ConsoleIODialog consoleIODialog = new ConsoleIODialog();
        consoleIODialog.printMessage("Autor: Michal Maziarz, 263913");
        PersonConsoleApp personConsoleApp = new PersonConsoleApp(consoleIODialog);
        personConsoleApp.runMainLoop();
    }
}