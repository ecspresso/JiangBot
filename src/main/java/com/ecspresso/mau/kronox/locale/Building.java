package com.ecspresso.mau.kronox.locale;

import java.util.List;
import java.util.stream.Stream;

public enum Building {
    NIAGARA("Niagara", "FLIK-0017"),
    G8("Gäddan", "FLIK_0003"),
    ORKANEN("Orkanen", "FLIK_0000");

    private final String name;
    private final String flik;

    Building(String name, String flik) {
        this.name = name;
        this.flik = flik;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getFlik() {
        return flik;
    }

    public static List<String> getBuildingNames() {
        return List.of(Stream.of(Building.values()).map(Building::toString).toArray(String[]::new));
    }

    public static Building convertString(String buildingName) {
        switch(buildingName) {
            // Alla giltiga alternativ.
            case "n" -> {
                return Building.NIAGARA;
            }
            case "g8" -> {
                return Building.G8;
            }
            case "o" -> {
                return Building.ORKANEN;
            }
            default -> {
                return null;
            }
        }
    }

}
