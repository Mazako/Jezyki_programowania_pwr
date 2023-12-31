/*
 *  Laboratorium 5
 *
 *   Autor: Michal Maziarz, 263 913
 *    Data: grudzień 2022 r.
 */
package pl.mazak.lab5.simulation;

public enum SimulationTypes {
    ONLY_ONE("Tylko jeden bus na moście"),
    HIGHWAY("Pełna autostrada"),
    BIDIRECTIONAL("Ruch w dwie strony (max 2 busy/strone)"),
    UNIDIRECTIONAL("Ruch w jedną stronę (max 2 busy)");

    private final String description;

    SimulationTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
