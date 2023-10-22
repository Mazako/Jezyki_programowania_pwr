/*
 *    Program: Glowna czesc konsolowej aplikacji w ktorej znajduje sie petla programu oraz menu z mozliwoscia wyboru opcji oraz metody ktore te opcje realizuja
 *    Plik: PersonConsoleApp.java
 *
 *   Autor: Michal Maziarz, 263913
 *    Data: pazdziernik 2022 r.
 */
package pl.mazak.lab2.view.console;

import pl.mazak.lab2.model.Animal;
import pl.mazak.lab2.model.AnimalException;
import pl.mazak.lab2.model.Species;

import java.util.Arrays;

class PersonConsoleApp {

    private final ConsoleIODialog consoleIODialog;
    private Animal currentAnimal;

    public PersonConsoleApp(ConsoleIODialog consoleIODialog) {
        this.consoleIODialog = consoleIODialog;
    }

    public void runMainLoop() {
        consoleIODialog.printMessage("Witaj!");
        boolean program = true;
        MenuItem option;
        while (program) {
            printOptions();
            int optionNumber = consoleIODialog.enterInt("Wybierz numer opcji: ");
            consoleIODialog.clearConsole();
            try {
                option = MenuItem.fromNumKey(optionNumber);
                switch (option) {
                    case EXIT -> program = false;
                    case ADD_NEW_ANIMAL -> createNewAnimal();
                    case DELETE_ANIMAL -> deleteCurrAnimal();
                    case MODIFY_DATA -> modifyAnimal();
                    case WRITE_TO_FILE -> writeToFile();
                    case READ_FROM_FILE -> readFromFile();
                    case WRITE_TO_BINARY -> writeToBinary();
                    case READ_FROM_BIANRY -> readFromBinary();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                consoleIODialog.printErrorMessage("Zla liczba! sprobuj jeszcze raz");
            } catch (AnimalException e) {
                consoleIODialog.printErrorMessage(e.getMessage());
            }
        }
        consoleIODialog.printMessage("Do widzenia!");

    }

    private void readFromBinary() throws AnimalException {
        String path = consoleIODialog.enterString("Podaj sciezke do pliku: ");
        Animal animal = Animal.readAnimalFromBinary(path);
        currentAnimal = animal.clone();

    }

    private void writeToBinary() throws AnimalException {
        if (currentAnimal == null) {
            consoleIODialog.printMessage("Aktualnie nie wybrano zadnego zwierzecia");
        } else {
            String path = consoleIODialog.enterString("Podaj sciezke do pliku (wraz z nazwa): ");
            Animal.writeAnimalToBinary(path, currentAnimal);
            consoleIODialog.printMessage("Pomyslnie zapisano do pliku " + path);
        }
    }

    private void readFromFile() throws AnimalException {
        String path = consoleIODialog.enterString("Podaj sciezke do pliku: ");
        Animal animal = Animal.readAnimalFromFile(path);
        currentAnimal = animal.clone();
    }

    private void writeToFile() throws AnimalException {
        if (currentAnimal == null) {
            consoleIODialog.printMessage("Aktualnie nie wybrano zadnego zwierzecia");
        } else {
            String path = consoleIODialog.enterString("Podaj sciezke do pliku (wraz z nazwa): ");
            Animal.writeAnimalToFile(path, currentAnimal);
            consoleIODialog.printMessage("Pomyslnie zapisano do pliku " + path);
        }
    }

    private void modifyAnimal() throws AnimalException {
        if (currentAnimal == null) {
            consoleIODialog.printInfoMessage("Nie ma aktualnie zadnego wybranego zwierzecia!");
        } else {
            printItemsToModify();
            EditItem editItem = EditItem.fromNumKey(
                    consoleIODialog.enterInt("Wpisz liczbe: ")
            );
            switch (editItem) {
                case AGE -> {
                    int age = consoleIODialog.enterInt("Wpisz wiek: ");
                    currentAnimal.setAge(age);
                }
                case NAME -> {
                    String name = consoleIODialog.enterString("Wpisz imie: ");
                    currentAnimal.setName(name);
                }
                case TYPE -> {
                    String type = consoleIODialog.enterString("Wpisz rodzaj: ");
                    currentAnimal.setType(type);
                }
                case WEIGHT -> {
                    double weigh = consoleIODialog.enterDouble("Wpisz wage (kg): ");
                    currentAnimal.setWeight(weigh);
                }
                case SPECIES -> {
                    String description = consoleIODialog.enterString("Wpisz opis(ssak, ryba, ptak, gad): ");
                    Species species = Species.fromDescription(description);
                    if (species == Species.UNKNOWN) {
                        consoleIODialog.printInfoMessage("UWAGA! nie znaleziono podanego gatunku. Zapisano jako gatunek nieznany");
                    }
                    currentAnimal.setSpecies(species);
                }

            }
        }
    }

    private void printItemsToModify() {
        consoleIODialog.printMessage("Dostepne opcje: ");
        Arrays.stream(EditItem.values())
                .forEach(System.out::println);
    }

    private void deleteCurrAnimal() {
        currentAnimal = null;
        consoleIODialog.printMessage("Pomyslnie usunieto zwierze");
    }

    private void createNewAnimal() throws AnimalException {
        Animal animal = new Animal();
        animal.setName(
                consoleIODialog.enterString("imie: ")
        );
        animal.setAge(
                consoleIODialog.enterInt("Wiek: ")
        );
        animal.setType(
                consoleIODialog.enterString("Rodzaj: ")
        );
        animal.setWeight(
                consoleIODialog.enterDouble("Waga(kg): ")
        );
        animal.setSpecies(
                Species.fromDescription(
                        consoleIODialog.enterString("Wpisz opis(ssak, ryba, ptak, gad): ")
                )
        );
        if (animal.getSpecies() == Species.UNKNOWN) {
            consoleIODialog.printInfoMessage("UWAGA! nie znaleziono podanego gatunku. Zapisano jako gatunek nieznany");
        }
        currentAnimal = animal;
    }

    private void printOptions() {
        consoleIODialog.printMessage("Aktualne zwierze: " + currentAnimal);
        for (MenuItem value : MenuItem.values()) {
            consoleIODialog.printMessage(value.toString());
        }
    }


    private enum MenuItem {
        ADD_NEW_ANIMAL(0, "Stworz nowe zwierze"),
        DELETE_ANIMAL(1, "Usun aktualne zwierze"),
        MODIFY_DATA(2, "zmodyfikuj dane zwierzecia"),
        READ_FROM_FILE(3, "Wczytaj nowe zwierze z pliku csv"),
        WRITE_TO_FILE(4, "Zapisz zwierze do pliku csv"),

        WRITE_TO_BINARY(5, "Zapisz do pliku binarnego"),

        READ_FROM_BIANRY(6, "Wczytaj z pliku binarnego"),
        EXIT(7, "Wyjscie");

        private final int numberKey;
        private final String description;

        MenuItem(int numberKey, String description) {
            this.numberKey = numberKey;
            this.description = description;
        }

        static MenuItem fromNumKey(int numberKey) {
            return MenuItem.values()[numberKey];
        }

        public int getNumberKey() {
            return numberKey;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return numberKey + " - " + description;
        }
    }

    private enum EditItem {
        NAME(0, "Imie"),
        TYPE(1, "Rodzaj"),
        AGE(2, "Wiek"),
        WEIGHT(3, "Waga"),
        SPECIES(4, "Gatunek");

        private final int numberKey;
        private final String description;

        EditItem(int numberKey, String description) {
            this.numberKey = numberKey;
            this.description = description;
        }

        static EditItem fromNumKey(int numberKey) {
            return EditItem.values()[numberKey];
        }

        public int getNumberKey() {
            return numberKey;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return numberKey + " - " + description;
        }

    }
}
