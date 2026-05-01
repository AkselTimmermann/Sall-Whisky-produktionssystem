package module;


import java.util.ArrayList;

public class Lager {
    private String navn;
    private String lokation;
    private int kapacitet;
    private ArrayList<Fad> fade = new ArrayList<>();

    public Lager(String navn, String lokation, int kapacitet) {
        this.navn = navn;
        this.lokation = lokation;
        this.kapacitet = kapacitet;
    }

    public void addFad(Fad fad) {
        if (!fade.contains(fad)) {
            fade.add(fad);
            fad.setLager(this);
        }
    }
    public void removeFad(Fad fad) {
        if (fade.contains(fad)) {
            fade.remove(fad);
            fad.setLager(null);
        }
    }

    public String getNavn() {
        return navn;
    }

    public String getLokation() {
        return lokation;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public ArrayList<Fad> getFade() {
        return new ArrayList<>(fade);
    }
}
