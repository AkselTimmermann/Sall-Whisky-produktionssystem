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

    @Override
    public String getId() {
        return fadId;
    }
}
