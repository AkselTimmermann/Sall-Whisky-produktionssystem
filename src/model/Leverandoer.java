package model;

import java.util.ArrayList;

public class Leverandoer {
    private String navn;
    private String lokation;


    public Leverandoer(String navn, String lokation) {
        this.navn = navn;
        this.lokation = lokation;
    }

    public String toString() {
        return navn;
    }
}
