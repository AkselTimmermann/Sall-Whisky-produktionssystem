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
        if (fadIndhold == null) {
            throw new IllegalStateException("Der skal være et ledigt fad");
        }
        destillat.tjekLiterNok(antalLiter);
        setDestillat(destillat);
        this.antalLiter = antalLiter;
        this.dato = dato;
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

    public void setDestillat(Destillat destillat) {
        if (!destillat.getPaafyldningsRegistreringer().contains(this)){
            this.destillat = destillat;
            destillat.addPaafyldningsregistrering(this);
        }
    }
}
