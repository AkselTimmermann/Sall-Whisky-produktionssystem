package model;

import java.util.ArrayList;

public class LagerPlads {
    private int pladsNr;
    private Hylde hylde;
    private ArrayList<LagerObjekt> lagerObjekter = new ArrayList<>();

    public LagerPlads(int pladsNr) {
        this.pladsNr = pladsNr;
    }

    public boolean erOptaget() {
        return !lagerObjekter.isEmpty();
    }

    //Metode der står får den faktiske placering af objektet
    public void placerIndhold(LagerObjekt objekt) {
        if (!lagerObjekter.contains(objekt)) {
            lagerObjekter.add(objekt);
            objekt.setlagerPLads(this);
        }
    }

    public void fjernIndhold(LagerObjekt lagerObjekt) {
        if (lagerObjekter.contains(lagerObjekt)) {
            lagerObjekter.remove(lagerObjekt);
            lagerObjekt.setlagerPLads(null);
        }
    }

    public int getPladsNr() {
        return pladsNr;
    }

    public ArrayList<LagerObjekt> getLagerObjekter() {
        return new ArrayList<>(lagerObjekter);
    }

    public void setHylde(Hylde hylde) {
        if (this.hylde != hylde) {
            Hylde oldHylde = this.hylde;
            if (oldHylde != null) {
                oldHylde.removePlads(this);
            }
            this.hylde = hylde;
            if (hylde != null) {
                hylde.addPlads(this);
            }
        }
    }

    public Hylde getHylde() {
        return hylde;
    }
}
