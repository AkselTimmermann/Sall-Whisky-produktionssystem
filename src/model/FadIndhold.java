package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FadIndhold {

    private Fad fad;

    private ArrayList<PaafyldningsRegistrering> paafyldningsRegistreringer = new ArrayList<>();
    private ArrayList<ModningsRegistrering> modningsRegistreringer = new ArrayList<>();
    private ArrayList<ProduktRegistrering> produktRegistreringer = new ArrayList<>();

    public FadIndhold(Fad fad) {
        if (fad == null) {
            throw new IllegalArgumentException("Fad skal angives");
        }
        fad.setNuvaerendeFadindhold(this);
        this.fad = fad;
    }

    public Set<MaltBatch> getDistinctMaltBatch() {
        Set<MaltBatch> maltBatch = new HashSet<>();
        for (PaafyldningsRegistrering pr : paafyldningsRegistreringer) {
            maltBatch.addAll(pr.getDestillat().getDistinctMaltBatch());
        }
        return maltBatch;
    }

    public Set<String> getDistinctDestillering() {
        Set<String> distinctDestilleringer = new HashSet<>();
        for (PaafyldningsRegistrering pr : paafyldningsRegistreringer) {
            distinctDestilleringer.addAll(pr.getDestillat().getDistinctDestillering());
        }
        return distinctDestilleringer;
    }


    public PaafyldningsRegistrering opretPaafyldningsRegistrering(double antalLiter, LocalDate dato, Destillat destillat, Medarbejder medarbejder) {
        PaafyldningsRegistrering paafyldningsRegistrering = new PaafyldningsRegistrering(antalLiter, dato, destillat, this, medarbejder);
        paafyldningsRegistreringer.add(paafyldningsRegistrering);
        if (!modningsRegistreringer.isEmpty()){
            modningsRegistreringer.getLast().addAntalLiter(antalLiter);
        }
        else initierModningsregistreringHvisIngen();
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

    public boolean isLagretMinimum3Aar(LocalDate produktDato){
        LocalDate paafyldningsDato = paafyldningsRegistreringer.getLast().getDato();
        return paafyldningsDato.plusYears(3).isBefore(produktDato);
    }

    public Fad getFad() {
        return fad;
    }

    public double getResterendeLiter() {
        initierModningsregistreringHvisIngen();
        return modningsRegistreringer.getLast().getAntalLiter();
    }

    public void reducerResterendeLiter(double antalLiter) {
        if (antalLiter <= 0) {
            throw new IllegalArgumentException("Antal liter skal være større end 0");
        }
        if (antalLiter>getResterendeLiter()){
            throw new IllegalArgumentException("Der er kun " + getResterendeLiter() + " tilbage, og du vil gerne bruge " + antalLiter);
        }
        initierModningsregistreringHvisIngen();

        modningsRegistreringer.getLast().reducerLiter(antalLiter);
        if (modningsRegistreringer.getLast().getAntalLiter()==0){
            this.fad.setNuvaerendeFadindhold(null);
        }

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
        initierModningsregistreringHvisIngen();
        return new ArrayList<>(modningsRegistreringer);
    }

    public double getSidstRegistreredeAlkoholProcent(){
        initierModningsregistreringHvisIngen();
        return getModningsRegistreringer().getLast().getAlkoholProcent();
    }

    public void initierModningsregistreringHvisIngen(){
        if (modningsRegistreringer.isEmpty()){
            opretModningsRegistrering(beregnStartAlkoholProcent(),LocalDate.now(),beregnStartAntalLiter(),"Automatisk oprettet modningsregistrering ud fra påfyldt destilat","Autogenereret modningsregistrering");
        }
    }


    public double getSamletPaafyldning() {
        double samlet = 0;

        for (PaafyldningsRegistrering registrering : paafyldningsRegistreringer) {
            samlet += registrering.getAntalLiter();
        }
        return samlet;
    }


    public String toString() {
        if (!getModningsRegistreringer().isEmpty()) {
            return "Fad ID: " + fad.getFadId() +
                    " | Antal liter: " + getModningsRegistreringer().getLast().getAntalLiter() +
                    " | Alkoholprocent: " + String.format("%.2f", getModningsRegistreringer().getLast().getAlkoholProcent()) +
                    " | sidste registrering: " + getModningsRegistreringer().getLast().getDato();

        }
        return "Hej";
    }

    public void addProduktRegistrering(ProduktRegistrering produktRegistrering) {
        if (!produktRegistreringer.contains(produktRegistrering)){
            produktRegistreringer.add(produktRegistrering);
            produktRegistrering.setFadindhold(this);
        }
    }
}
