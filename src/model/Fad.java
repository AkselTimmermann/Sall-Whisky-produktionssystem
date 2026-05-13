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
    private FadIndhold fadIndhold;

    public Fad(String fadId, String traaType, String beskrivelse, double stoerrelse, Leverandoer leverandoer) {
        this.fadId = fadId;
        this.traaType = traaType;
        this.beskrivelse = beskrivelse;
        this.stoerrelse = stoerrelse;
        this.status = FadStatus.DEAKTIVERET;
        this.leverandoer = leverandoer;
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

    public FadIndhold getFadIndhold() {
        return fadIndhold;
    }

    public void setFadIndhold(FadIndhold fadIndhold) {
        if (this.fadIndhold!=fadIndhold){
            if (this.fadIndhold!=null){
                throw new IllegalArgumentException("Fadet har allerede et fadindhold");
            }
            this.fadIndhold=fadIndhold;
            fadIndhold.setFad(this);
        }
    }

    @Override
    public String getId() {
        return fadId;
    }
}
