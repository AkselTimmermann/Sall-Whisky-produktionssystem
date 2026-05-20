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
    private FadIndhold nuvaerendeFadindhold;
    private ArrayList<FadIndhold> gammeltFadIndholdListe = new ArrayList<>();

    public Fad(String fadId, String traaType, String beskrivelse, double stoerrelse, Leverandoer leverandoer) {
        this.fadId = fadId;
        this.traaType = traaType;
        this.beskrivelse = beskrivelse;
        this.stoerrelse = stoerrelse;
        this.leverandoer = leverandoer;
    }

    public void addGammmeltFadIndhold(FadIndhold fadIndhold) {
        if (!gammeltFadIndholdListe.contains(fadIndhold)) {
            gammeltFadIndholdListe.add(fadIndhold);
        }
    }

    public FadIndhold getAktivtFadIndhold() {
        return nuvaerendeFadindhold;
    }

    public ArrayList<FadIndhold> getGammeltFadIndholdListe() {
        return new ArrayList<>(gammeltFadIndholdListe);
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
                "Status: " + getStatus();
    }

    public void setNuvaerendeFadindhold(FadIndhold nuvaerendeFadindhold) {
        if (this.nuvaerendeFadindhold!=null){
            if (this.nuvaerendeFadindhold.getResterendeLiter()==0){
                addGammmeltFadIndhold(this.nuvaerendeFadindhold);
            }
            else {
                throw new IllegalArgumentException("Der er allerede oprettet indhold til dette fad");
            }
        }
        this.nuvaerendeFadindhold = nuvaerendeFadindhold;
    }

    public FadStatus getStatus() {
        if (status==FadStatus.DEAKTIVERET) return FadStatus.DEAKTIVERET;
        else if (nuvaerendeFadindhold == null) {
            return FadStatus.INAKTIV;
        } else if (nuvaerendeFadindhold.getResterendeLiter()>0) {
            return FadStatus.AKTIV;
        }
        else return FadStatus.INAKTIV;
    }
}
