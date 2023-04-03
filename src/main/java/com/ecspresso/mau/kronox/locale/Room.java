package com.ecspresso.mau.kronox.locale;

import java.util.Arrays;
import java.util.List;

public enum Room {
    NIA0301("NI:A0301", Building.NIAGARA),
    NIA0322("NI:A0322", Building.NIAGARA),
    NIA0401("NI:A0401", Building.NIAGARA),
    NIA0422("NI:A0422", Building.NIAGARA),
    NIB0303("NI:B0303", Building.NIAGARA),
    NIB0305("NI:B0305", Building.NIAGARA),
    NIB0314("NI:B0314", Building.NIAGARA),
    NIB0317("NI:B0317", Building.NIAGARA),
    NIC0205("NI:C0205", Building.NIAGARA),
    NIC0301("NI:C0301", Building.NIAGARA),
    NIC0306("NI:C0306", Building.NIAGARA),
    NIC0309("NI:C0309", Building.NIAGARA),
    NIC0312("NI:C0312", Building.NIAGARA),
    NIA0614("NI:A0614", Building.NIAGARA),
    G8236("G8:236", Building.G8),
    G8238("G8:238", Building.G8),
    G8249("G8:249", Building.G8),
    G8252("G8:252", Building.G8),
    G8253("G8:253", Building.G8),
    G8255("G8:255", Building.G8),
    G8256("G8:256", Building.G8),
    G8260("G8:260", Building.G8),
    ORB230("OR:B230", Building.ORKANEN),
    ORB338("OR:B338", Building.ORKANEN),
    ORB422("OR:B422", Building.ORKANEN),
    ORC232("OR:C232", Building.ORKANEN),
    ORC235("OR:C235", Building.ORKANEN),
    ORC236("OR:C236", Building.ORKANEN),
    ORC377("OR:C377", Building.ORKANEN),
    ORD377("OR:D377", Building.ORKANEN),
    ORD436("OR:D436", Building.ORKANEN),
    ORD438("OR:D438", Building.ORKANEN),
    ORE235("OR:E235", Building.ORKANEN),
    ORE336("OR:E336", Building.ORKANEN),
    ORE477("OR:E477", Building.ORKANEN),
    ORF416("OR:F416", Building.ORKANEN);

    private final String name;
    private final Building building;

    Room(String name, Building building) {
        this.name = name;
        this.building = building;
    }

    public static List<Room> filter(String building) {
        return Arrays.stream(values()).filter(room -> room.name().equals(building)).toList();
    }

    @Override
    public String toString() {
        return name;
    }

    public Building getBuilding() {
        return building;
    }

    public String getRoomName() {
        return name;
    }
}
