package model;

import java.util.ArrayList;

public class Fad implements LagerObjekt {
    private String fadId;
    private String traaType;
    private String beskrivelse;
    private double stoerrelse;
    private FadStatus status;
    private ArrayList<PaafyldningsRegistrering> paafyldningsRegistreringer = new ArrayList<>();

    public Fad(String fadId, String traaType, String beskrivelse, double stoerrelse) {
        this.fadId = fadId;
        this.traaType = traaType;
        this.beskrivelse = beskrivelse;
        this.stoerrelse = stoerrelse;
        this.status = FadStatus.DEAKTIVERET;
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


    @Override
    public String getId() {
        return fadId;
    }

    public ArrayList<PaafyldningsRegistrering> getPaafyldningsRegistreringer() {
        return new ArrayList<>(paafyldningsRegistreringer);
    }
}
