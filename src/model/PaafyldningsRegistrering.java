package model;

import java.time.LocalDate;

public class PaafyldningsRegistrering {
    private double antalLiter;
    private LocalDate dato;
    private Destillat destillat;
    private Fad fad;
    private Medarbejder medarbejder;

    public PaafyldningsRegistrering(double antalLiter, LocalDate dato, Destillat destillat, Fad fad, Medarbejder medarbejder) {
        if (destillat == null) {
            throw new IllegalStateException("Destillering skal være oprettet");
        }
        if (antalLiter > destillat.getAntalLiter()) {
            throw new IllegalStateException("Antal liter overstiger mængden af ledig destillering");
        }
        if (fad == null) {
            throw new IllegalStateException("Der skal være et ledigt fad");
        }
        this.antalLiter = antalLiter;
        this.dato = dato;
        this.destillat = destillat;
        this.fad = fad;
        this.medarbejder = medarbejder;
    }

    public double getAntalLiter() {
        return antalLiter;
    }

    public LocalDate getDato() {
        return dato;
    }

    public Destillat getDestillat() {
        return destillat;
    }

    public Fad getFad() {
        return fad;
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }
}
