package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Fad implements LagerObjekt {
    private String fadId;
    private String traaType;
    private String beskrivelse;
    private double stoerrelse;
    private FadStatus status;
    private ArrayList<PaafyldningsRegistrering> paafyldningsRegistreringer = new ArrayList<>();
    private ArrayList<ModningsRegistrering> modningsRegistreringer = new ArrayList<>();
    private Leverandoer leverandoer;

    public Fad(String fadId, String traaType, String beskrivelse, double stoerrelse, Leverandoer leverandoer) {
        this.fadId = fadId;
        this.traaType = traaType;
        this.beskrivelse = beskrivelse;
        this.stoerrelse = stoerrelse;
        this.status = FadStatus.DEAKTIVERET;
        setLeverandoer(leverandoer);
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

    public PaafyldningsRegistrering opretPaafyldningsRegistrering(double antalLiter, LocalDate dato, Destillering destillering, Medarbejder medarbejder) {
        PaafyldningsRegistrering paafyldningsRegistrering = new PaafyldningsRegistrering(antalLiter, dato, destillering, this, medarbejder);
        paafyldningsRegistreringer.add(paafyldningsRegistrering);
        return paafyldningsRegistrering;
    }

    public void fjernPaafyldningsRegistrering(PaafyldningsRegistrering paafyldningsRegistrering) {
        if (paafyldningsRegistreringer.contains(paafyldningsRegistrering)) {
            paafyldningsRegistreringer.remove(paafyldningsRegistrering);
        }
    }

    public ModningsRegistrering opretModningsRegistrering(double alkoholProcent, LocalDate dato, double antalLiter, String note, String titel){
        ModningsRegistrering modningsRegistrering = new ModningsRegistrering(alkoholProcent, dato, antalLiter, note, titel);
        modningsRegistreringer.add(modningsRegistrering);
        return modningsRegistrering;
    }

    public void fjernModningsRegistrering(ModningsRegistrering modningsRegistrering) {
        if (modningsRegistreringer.contains(modningsRegistrering)) {
            modningsRegistreringer.remove(modningsRegistrering);
        }
    }

    public ArrayList<PaafyldningsRegistrering> getPaafyldningsRegistreringer() {
        return new ArrayList<>(paafyldningsRegistreringer);
    }

    public ArrayList<ModningsRegistrering> getModningsRegistreringer() {
        return new ArrayList<>(modningsRegistreringer);
    }

    public void setLeverandoer(Leverandoer leverandoer) {
        if (this.leverandoer == null){
            this.leverandoer=leverandoer;
            leverandoer.addFad(this);
        }
    }
}
