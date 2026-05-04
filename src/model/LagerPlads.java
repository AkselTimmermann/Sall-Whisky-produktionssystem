package model;

public class LagerPlads {
    private int pladsNr;
    private LagerObjekt indhold;

    public LagerPlads(int pladsNr) {
        this.pladsNr = pladsNr;
    }

    public boolean erOptaget() {
        return indhold != null;
    }

    //Metode der står får den faktiske placering af objektet + tjek om pladsen allerede er optaget.
    public void placerIndhold(LagerObjekt objekt) {
        if (erOptaget()) {
            throw new IllegalStateException("Pladsen er allerede optaget");
        }
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
