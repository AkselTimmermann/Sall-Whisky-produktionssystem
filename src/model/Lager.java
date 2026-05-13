package model;


import java.util.ArrayList;

public class Lager {
    private String navn;
    private String lokation;
    private int stoerrelse;
    private ArrayList<Reol> reoler = new ArrayList<>();

    public Lager(String navn, String lokation, int kapacitet) {
        this.navn = navn;
        this.lokation = lokation;
        this.stoerrelse = kapacitet;
    }
    /*
    //metode til at placere objekt på en lagerPlads
    //Metoden kalder først findPlads for at sikre, at pladsen findes
    //Derefter kalder den placerIndhold, der tjekker om pladsen er optaget, hvis ikke, placeres objektet.
    public void placerObjekt(LagerObjekt objekt, int reolNr, int hyldeNr, int pladsNr) {
        LagerPlads plads = findPlads(reolNr, hyldeNr, pladsNr);
        plads.placerIndhold(objekt);
    }

    //Metode til at tjekke om plads eksistere i systemet
    //Skal bruges til at kunne placere et objekt på en plads.
    public LagerPlads findPlads(int reolNr, int hyldeNr, int pladsNr) {
        for (Reol reol : reoler) {
            if (reol.getReolNr() == reolNr) {
                for (Hylde hylde : reol.getHylder()) {
                    if (hylde.getHyldeNr() == hyldeNr) {
                        for (LagerPlads plads : hylde.getPladser()) {
                            if (plads.getPladsNr() == pladsNr) {
                                return plads;
                            }
                        }
                    }
                }
            }
        }
        throw new IllegalStateException("Plads findes ikke i systemet");
    }

    //Metode til at fjerne objekt på en lagerPlads
    public void fjernObjekt(LagerObjekt objekt) {
        for (Reol reol : reoler) {
            for (Hylde hylde : reol.getHylder()) {
                for (LagerPlads lagerPlads : hylde.getPladser()) {
                    if (lagerPlads.getIndhold() == objekt) {
                        lagerPlads.fjernIndhold();
                        return;
                    }
                }
            }
        }
        throw new IllegalStateException("Objekt ikke fundet");
    }

    //Metode til at finde placering på lageret for et objekt
    public String findPlacering(LagerObjekt objekt) {
        for (Reol reol : reoler) {
            for (Hylde hylde : reol.getHylder()) {
                for (LagerPlads lagerPlads : hylde.getPladser()) {
                    if (lagerPlads.getIndhold() == objekt) {
                        return "Reol " + reol.getReolNr() +
                                ", Hylde " + hylde.getHyldeNr() +
                                ", Plads " + lagerPlads.getPladsNr();
                    }
                }
            }
        }
        return "Placering ikke fundet";
    }
     */

    public void addReol(Reol reol) {
        if (!reoler.contains(reol)) {
            reoler.add(reol);
            reol.setLager(this);
        }
    }
    public void removeReol(Reol reol) {
        if (reoler.contains(reol)) {
            reoler.remove(reol);
            reol.setLager(null);
        }
    }

    public String getNavn() {
        return navn;
    }

    public String getLokation() {
        return lokation;
    }

    public int getStoerrelse() {
        return stoerrelse;
    }

    public ArrayList<Reol> getReoler() {
        return new ArrayList<>(reoler);
    }
}
