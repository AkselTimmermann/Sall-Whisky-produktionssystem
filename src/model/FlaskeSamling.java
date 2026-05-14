package model;

import java.util.ArrayList;

public class FlaskeSamling implements LagerObjekt {
    private int samlingsNr;
    private LagerPlads lagerPlads;
    private ArrayList<Flaske> flasker = new ArrayList<>();

    public FlaskeSamling(int samlingsNr, ArrayList<Flaske> flasker) {
        this.samlingsNr = samlingsNr;
        this.flasker = flasker;
    }

    public void addFlaske(Flaske flaske) {
        if (!flasker.contains(flaske)) {
            flasker.add(flaske);
        }
    }

    public void fjernFlaske(Flaske flaske) {
        if (flasker.contains(flaske)) {
            flasker.remove(flaske);
        }
    }

    public int getSamlingsNr() {
        return samlingsNr;
    }

    public ArrayList<Flaske> getFlasker() {
        return new ArrayList<>(flasker);
    }

    @Override
    public LagerPlads getLagerPlads() {
        return null;
    }

    @Override
    public void setlagerPLads(LagerPlads lagerPlads) {
        if (this.lagerPlads != lagerPlads) {
            LagerPlads oldPlads = this.lagerPlads;
            if (oldPlads != null) {
                oldPlads.fjernIndhold(this);
            }
            this.lagerPlads = lagerPlads;
            if (lagerPlads != null) {
                lagerPlads.placerIndhold(this);
            }
        }
    }

    @Override
    public String getId() {
        return "";
    }
    public String toString() {
        return "Nr: " + samlingsNr;
    }
}
