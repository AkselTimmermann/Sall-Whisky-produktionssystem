package model;

import java.time.LocalDate;

public class PaafyldningsRegistrering {
    private double antalLiter;
    private LocalDate dato;
    private Destillering destillering;
    private Fad fad;

    public PaafyldningsRegistrering(double antalLiter, LocalDate dato, Destillering destillering, Fad fad) {
        if (destillering == null) {
            throw new IllegalStateException("Destillering skal være oprettet");
        }
        if (antalLiter > destillering.getMaengdeVaeske()) {
            throw new IllegalStateException("Antal liter overstiger mængden af ledig destillering");
        }
        if (fad == null) {
            throw new IllegalStateException("Der skal være et ledigt fad");
        }
        this.antalLiter = antalLiter;
        this.dato = dato;
        this.destillering = destillering;
        this.fad = fad;
    }

    public double getAntalLiter() {
        return antalLiter;
    }

    public LocalDate getDato() {
        return dato;
    }

    public Destillering getDestillering() {
        return destillering;
    }

    public Fad getFad() {
        return fad;
    }
}
