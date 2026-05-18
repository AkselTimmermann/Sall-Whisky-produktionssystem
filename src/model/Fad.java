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
    private FadIndhold fadindholdContainer;
    private ArrayList<FadIndhold> tidligereFadIndholdListe = new ArrayList<>();

    public Fad(String fadId, String traaType, String beskrivelse, double stoerrelse, Leverandoer leverandoer) {
        this.fadId = fadId;
        this.traaType = traaType;
        this.beskrivelse = beskrivelse;
        this.stoerrelse = stoerrelse;
        this.leverandoer = leverandoer;
    }

    public void setFadindholdContainer(FadIndhold fadIndhold){
        if (this.fadindholdContainer==null){
            this.fadindholdContainer=fadIndhold;
        }
    }

    public void removeFadindholdContainer(){
        if (fadindholdContainer != null){
            tidligereFadIndholdListe.add(fadindholdContainer);
            fadindholdContainer = null;
        }
    }


    public FadIndhold getAktivtFadIndhold() {
        return fadindholdContainer;
    }

    public ArrayList<FadIndhold> getFadIndholdListe() {
        return new ArrayList<>(tidligereFadIndholdListe);
    }


    public FadStatus getStatus() {
        if (status==FadStatus.DEAKTIVERET) return FadStatus.DEAKTIVERET;
        else if (fadindholdContainer == null) {
            return FadStatus.INAKTIV;
        } else if (fadindholdContainer.getAntalLiter()>0) {
            return FadStatus.AKTIV;
        }
        else return FadStatus.INAKTIV;
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

        return stoerrelse - aktivtIndhold.getAntalLiter();
    }

    public void retireFad() {
        this.status = FadStatus.DEAKTIVERET;
    }




    @Override
    public String toString() {
        return "FadID: " + fadId + " | " +
                "Trætype: " + traaType + " | " +
                "Størrelse: " + stoerrelse + " | " +
                "Status: " + getStatus();
    }
}
