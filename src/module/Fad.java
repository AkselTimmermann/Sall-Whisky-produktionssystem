package module;

public class Fad {
    private String fadId;
    private String traaType;
    private String beskrivelse;
    private double stoerrelse;
    private String plads;
    private Lager lager;

    public Fad(String fadId, String traaType, String beskrivelse, double stoerrelse, String plads) {
        this.fadId = fadId;
        this.traaType = traaType;
        this.beskrivelse = beskrivelse;
        this.stoerrelse = stoerrelse;
        this.plads = plads;
    }

    public void setLager(Lager lager) {
        if (this.lager != lager) {
            Lager oldLager = this.lager;
            if (oldLager != null) {
                oldLager.removeFad(this);
            }
            this.lager = lager;
            if (lager != null) {
               lager.addFad(this);
            }
        }
    }


    public String getFadId() {
        return fadId;
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

    public String getPlads() {
        return plads;
    }

    public Lager getLager() {
        return lager;
    }
}
