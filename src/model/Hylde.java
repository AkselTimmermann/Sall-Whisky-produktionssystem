package model;

import java.util.ArrayList;

public class Hylde {
    private int hyldeNr;
    private ArrayList<LagerPlads> pladser = new ArrayList<>();

    public Hylde(int hyldeNr) {
        this.hyldeNr = hyldeNr;
    }

    public int getHyldeNr() {
        return hyldeNr;
    }

    public void addPlads(LagerPlads plads) {
        if (!pladser.contains(plads)) {
            pladser.add(plads);
        }
    }

    public ArrayList<LagerPlads> getPladser() {
        return new ArrayList<>(pladser);
    }
}
