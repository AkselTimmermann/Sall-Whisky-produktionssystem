package model;

public class LagerPlads {
    private int pladsNr;
    private LagerObjekt indhold;

    public LagerPlads(int pladsNr) {
        this.pladsNr = pladsNr;
    }

    //Metode der står får den faktiske placering af objektet
    public void placerIndhold(LagerObjekt objekt) {
        this.indhold = objekt;
    }

    public void fjernIndhold() {
        this.indhold = null;
    }

    public int getPladsNr() {
        return pladsNr;
    }

    public LagerObjekt getIndhold() {
        return indhold;
    }
}
