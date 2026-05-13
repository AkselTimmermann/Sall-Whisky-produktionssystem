package model;

import java.util.ArrayList;

public class Hylde {
    private int hyldeNr;
    private Reol reol;
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
            plads.setHylde(this);
        }
    }
    public void removePlads(LagerPlads plads) {
        if (pladser.contains(plads)) {
            pladser.remove(plads);
            plads.setHylde(null);
        }
    }

    public ArrayList<LagerPlads> getPladser() {
        return new ArrayList<>(pladser);
    }
    public void setReol(Reol reol) {
        if (this.reol != reol) {
            Reol oldReol = this.reol;
            if (oldReol != null) {
                oldReol.removeHylde(this);
            }
            this.reol = reol;
            if (reol != null) {
                reol.addHylde(this);
            }
        }
    }

    public Reol getReol() {
        return reol;
    }
}
