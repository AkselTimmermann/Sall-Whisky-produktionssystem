package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class FadIndhold {
    private ArrayList<PaafyldningsRegistrering> paafyldningsRegistreringer = new ArrayList<>();
    private ArrayList<ModningsRegistrering> modningsRegistreringer = new ArrayList<>();
    private Fad fad;

    public FadIndhold(Fad fad) {
        if (fad == null) {
            throw new IllegalArgumentException("Fad skal angives");
        }
        this.fad = fad;
        fad.addFadIndhold(this);
    }

    public PaafyldningsRegistrering opretPaafyldningsRegistrering(double antalLiter, LocalDate dato, Destillat destillat, Medarbejder medarbejder) {
        PaafyldningsRegistrering paafyldningsRegistrering = new PaafyldningsRegistrering(antalLiter, dato, destillat, this, medarbejder);
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

    public double beregnStartAlkoholProcent(){
        return beregnStartAlkoholMaengde()/beregnStartAntalLiter();
    }

    public double beregnStartAntalLiter(){
        return paafyldningsRegistreringer.stream().mapToDouble(paafyldningsRegistrering->paafyldningsRegistrering.getAntalLiter()).sum();
    }

    private double beregnStartAlkoholMaengde(){
        return paafyldningsRegistreringer.stream().mapToDouble(
                paafyldningsRegistrering
                        ->paafyldningsRegistrering.getAntalLiter()*paafyldningsRegistrering.getDestillat().getAlkoholProcent()).sum();
    }

    public Fad getFad() {
        return fad;
    }

    /*public void setFad(Fad fad) {
        if (this.fad!=fad){
            fad.setFadIndhold(this);
            this.fad = fad;
        }
    }*/

    public ArrayList<PaafyldningsRegistrering> getPaafyldningsRegistreringer() {
        return new ArrayList<>(paafyldningsRegistreringer);
    }

    public ArrayList<ModningsRegistrering> getModningsRegistreringer() {
        return new ArrayList<>(modningsRegistreringer);
    }
}
