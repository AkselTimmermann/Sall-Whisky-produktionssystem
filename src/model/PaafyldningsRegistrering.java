package model;

import java.time.LocalDate;

public class PaafyldningsRegistrering {
    private double antalLiter;
    private LocalDate dato;
    private Paafyldningsvaeske paafyldningsvaeske;
    private FadIndhold fadIndhold;
    private Medarbejder medarbejder;

    public PaafyldningsRegistrering(double antalLiter, LocalDate dato, Paafyldningsvaeske paafyldningsvaeske, FadIndhold fadIndhold, Medarbejder medarbejder) {
        if (paafyldningsvaeske == null) {
            throw new IllegalStateException("Destillat eller fadindhold der ønskes paafyldt skal være oprettet");
        }
        if (fadIndhold == null) {
            throw new IllegalStateException("Der skal være et ledigt fad");
        }
        paafyldningsvaeske.reducerResterendeLiter(antalLiter);
        this.antalLiter = antalLiter;
        this.dato = dato;
        setPaafyldningsvaeske(paafyldningsvaeske);
        this.fadIndhold = fadIndhold;
        this.medarbejder = medarbejder;
    }

    public double getAntalLiter() {
        return antalLiter;
    }

    public LocalDate getDato() {
        return dato;
    }

    public LocalDate getSenesteModningsStartDato(){
        if (paafyldningsvaeske instanceof FadIndhold){
            return ((FadIndhold) paafyldningsvaeske).getSenesteModningsStartDato();
        }
        else {
            return dato;
        }
    }

    public Paafyldningsvaeske getPaafyldningsvaeske() {
        return paafyldningsvaeske;
    }

    public FadIndhold getFadIndhold() {
        return fadIndhold;
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }

    public void setPaafyldningsvaeske(Paafyldningsvaeske paafyldningsvaeske) {
        if (!paafyldningsvaeske.getPaafyldningsRegistreringerTil().contains(this)){
            this.paafyldningsvaeske = paafyldningsvaeske;
            paafyldningsvaeske.addPaafyldningsRegistreringTil(this);
        }
    }
}
