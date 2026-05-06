package model;

import java.util.ArrayList;

public class Reol {
    private int reolNr;
    private ArrayList<Hylde> hylder = new ArrayList<>();

    public Reol(int reolNr) {
        this.reolNr = reolNr;
    }

    public int getReolNr() {
        return reolNr;
    }

    public void addHylde(Hylde hylde) {
        if (!hylder.contains(hylde)) {
            hylder.add(hylde);
        }
    }

    public ArrayList<Hylde> getHylder() {
        return new ArrayList<>(hylder);
    }
}
