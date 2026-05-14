package model;

import java.util.ArrayList;

public class Reol {
    private int reolNr;
    private Lager lager;
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
            hylde.setReol(this);
        }
    }

    public void removeHylde(Hylde hylde) {
        if (hylder.contains(hylde)) {
            hylder.remove(hylde);
            hylde.setReol(null);
        }
    }
    public void setLager(Lager lager) {
        if (this.lager != lager) {
            Lager oldLager = this.lager;
            if (oldLager != null) {
                oldLager.removeReol(this);
            }
            this.lager = lager;
            if (lager != null) {
                lager.addReol(this);
            }
        }
    }

    public ArrayList<Hylde> getHylder() {
        return new ArrayList<>(hylder);
    }
}
