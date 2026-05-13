package model;

import java.util.ArrayList;

public class FlaskeSamling {
    private int samlingsNr;
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
}
