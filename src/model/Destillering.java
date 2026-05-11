package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Destillering {

    private String newMakeNr;
    private LocalDate startDato;
    private LocalDate slutDato;
    private double antalLiter;
    private double alkoholProcent;
    private String rygeMateriale;
    private String kommentar;
    private MaltBatch maltBatch;
    private Medarbejder medarbejder;
    private ArrayList<Destillat> destillater = new ArrayList<>();

    // Kommentar
    public Destillering(String newMakeNr, LocalDate startDato, LocalDate slutDato, double antalLiter,
                        double alkoholProcent, String rygeMateriale, String kommentar, MaltBatch maltBatch, Medarbejder medarbejder) {

        this.newMakeNr = newMakeNr;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.antalLiter = antalLiter;
        this.alkoholProcent = alkoholProcent;
        this.rygeMateriale = rygeMateriale;
        this.kommentar = kommentar;
        setMaltBatch(maltBatch);
        this.medarbejder = medarbejder;
    }

    public void reducerResterendeLiter(double liter) {
        if (liter > antalLiter) {
            throw new IllegalArgumentException("Ikke nok væske");
        }
        this.antalLiter -= liter;
    }

    public String getNewMakeNr() {
        return newMakeNr;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public double getAntalLiter() {
        return antalLiter;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public String getRygeMateriale() {
        return rygeMateriale;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setMaltBatch(MaltBatch maltBatch) {
        if (this.maltBatch==null){
            this.maltBatch = maltBatch;
            maltBatch.addDestillering(this);
        }
    }

    public void addDestillat(Destillat destillat) {
        if (!destillater.contains(destillat)){
            destillater.add(destillat);
            destillat.addDestillering(this);
        }
    }
}
