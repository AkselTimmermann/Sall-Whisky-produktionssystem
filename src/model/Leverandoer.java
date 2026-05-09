package model;

import java.util.ArrayList;

public class Leverandoer {
    private String navn;
    private String lokation;
    private ArrayList<Fad> fade = new ArrayList<>();

    public Leverandoer(String navn, String lokation) {
        this.navn = navn;
        this.lokation = lokation;
    }

    public void addFad(Fad fad){
        if (!fade.contains(fad)){
            fade.add(fad);
            fad.setLeverandoer(this);
        }
    }
}
