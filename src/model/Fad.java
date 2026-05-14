package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Fad implements LagerObjekt {
    private String fadId;
    private String traaType;
    private String beskrivelse;
    private double stoerrelse;
    private FadStatus status;
    private Leverandoer leverandoer;
    private LagerPlads lagerPlads;

    private ArrayList<FadIndhold> fadIndholdListe = new ArrayList<>();

    public Fad(String fadId, String traaType, String beskrivelse, double stoerrelse, Leverandoer leverandoer) {
        this.fadId = fadId;
        this.traaType = traaType;
        this.beskrivelse = beskrivelse;
        this.stoerrelse = stoerrelse;
        this.status = FadStatus.DEAKTIVERET;
        this.leverandoer = leverandoer;
    }

    public void addFadIndhold(FadIndhold fadIndhold) {
        if (!fadIndholdListe.contains(fadIndhold)) {
            fadIndholdListe.add(fadIndhold);
        }
    }

    public FadIndhold getAktivtFadIndhold() {
        if (fadIndholdListe.isEmpty()) {
            return null;
        }
        return fadIndholdListe.getLast();
    }

    public ArrayList<FadIndhold> getFadIndholdListe() {
        return new ArrayList<>(fadIndholdListe);
    }


    public FadStatus getStatus() {
        return status;
    }

    public String getTraaType() {
        return traaType;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public double getStoerrelse() {
        return stoerrelse;
    }

    public String getFadId() {
        return fadId;
    }

    public Leverandoer getLeverandoer() {
        return leverandoer;
    }

    @Override
    public LagerPlads getLagerPlads() {
        return lagerPlads;
    }

    @Override
    public void setlagerPLads(LagerPlads lagerPlads) {
        if (this.lagerPlads != lagerPlads) {
            LagerPlads oldPlads = this.lagerPlads;
            if (oldPlads != null) {
                oldPlads.fjernIndhold(this);
            }
            this.lagerPlads = lagerPlads;
            if (lagerPlads != null) {
                lagerPlads.placerIndhold(this);
            }
        }
    }

    @Override
    public String getId() {
        return fadId;
    }

    public double getLedigKapacitet() {
        FadIndhold aktivtIndhold = getAktivtFadIndhold();

        if (aktivtIndhold == null) {
            return stoerrelse;
        }

        return stoerrelse - aktivtIndhold.beregnStartAntalLiter();
    }

    public void setStatus(FadStatus status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "FadID: " + fadId + " | " +
                "Trætype: " + traaType + " | " +
                "Størrelse: " + stoerrelse + " | " +
                "Status: " + status;
    }
}
