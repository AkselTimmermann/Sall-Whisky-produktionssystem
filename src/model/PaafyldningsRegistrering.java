package model;

import java.time.LocalDate;

public class PaafyldningsRegistrering {
    private double antalLiter;
    private LocalDate dato;
    private Destillat destillat;
    private FadIndhold fadIndhold;
    private Medarbejder medarbejder;

    public PaafyldningsRegistrering(double antalLiter, LocalDate dato, Destillat destillat, FadIndhold fadIndhold, Medarbejder medarbejder) {
        if (destillat == null) {
            throw new IllegalStateException("Destillering skal være oprettet");
        }
        if (antalLiter > destillat.getAntalLiter()) {
            throw new IllegalStateException("Antal liter overstiger mængden af ledig destillering");
        }
        if (fadIndhold == null) {
            throw new IllegalStateException("Der skal være et ledigt fad");
        }
        this.antalLiter = antalLiter;
        this.dato = dato;
        this.destillat = destillat;
        this.fadIndhold = fadIndhold;
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

    public FadIndhold getFadIndhold() {
        return fadIndhold;
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }
}
