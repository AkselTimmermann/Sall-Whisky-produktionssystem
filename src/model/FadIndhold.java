package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class FadIndhold implements Paafyldningsvaeske {

    private Fad fad;

    private ArrayList<PaafyldningsRegistrering> paafyldningsRegistreringerFra = new ArrayList<>();
    private ArrayList<PaafyldningsRegistrering> paafyldningsRegistreringerTil = new ArrayList<>();
    private ArrayList<ModningsRegistrering> modningsRegistreringer = new ArrayList<>();
    private ArrayList<ProduktRegistrering> produktRegistreringer = new ArrayList<>();

    public FadIndhold(Fad fad) {
        if (fad == null) {
            throw new IllegalArgumentException("Fad skal angives");
        }
        this.fad = fad;
        fad.addFadIndhold(this);
    }



    public PaafyldningsRegistrering opretPaafyldningsRegistrering(double antalLiter, LocalDate dato, Paafyldningsvaeske paafyldningsvaeske, Medarbejder medarbejder) {
        PaafyldningsRegistrering paafyldningsRegistrering = new PaafyldningsRegistrering(antalLiter, dato, paafyldningsvaeske, this, medarbejder);
        paafyldningsRegistreringerFra.add(paafyldningsRegistrering);
        return paafyldningsRegistrering;
    }

    public void fjernPaafyldningsRegistrering(PaafyldningsRegistrering paafyldningsRegistrering) {
        if (paafyldningsRegistreringerFra.contains(paafyldningsRegistrering)) {
            paafyldningsRegistreringerFra.remove(paafyldningsRegistrering);
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
        return paafyldningsRegistreringerFra.stream().mapToDouble(paafyldningsRegistrering->paafyldningsRegistrering.getAntalLiter()).sum();
    }

    private double beregnStartAlkoholMaengde(){
        return paafyldningsRegistreringerFra.stream().mapToDouble(
                paafyldningsRegistrering
                        ->paafyldningsRegistrering.getAntalLiter()*paafyldningsRegistrering.getPaafyldningsvaeske().getAlkoholProcent()).sum();
    }

    public boolean isLagretMinimum3Aar(LocalDate produktDato){
        LocalDate paafyldningsDato = getSenesteModningsStartDato();
        return paafyldningsDato.plusYears(3).isBefore(produktDato);
    }

    public LocalDate getSenesteModningsStartDato(){
        LocalDate modningsStartDato = paafyldningsRegistreringerFra.getLast().getSenesteModningsStartDato();
        for (PaafyldningsRegistrering paafyldningsRegistrering :paafyldningsRegistreringerFra){
            LocalDate modningsStartDatoForPaafyldningsRegistrering = paafyldningsRegistrering.getSenesteModningsStartDato();
            if (modningsStartDatoForPaafyldningsRegistrering.isAfter(modningsStartDato)){
                modningsStartDato = modningsStartDatoForPaafyldningsRegistrering;
            }
        }
        return modningsStartDato;
    }

    public Fad getFad() {
        return fad;
    }

    public ArrayList<Fad> getFade(){
        ArrayList<Fad> fade = new ArrayList<>();
        fade.add(fad);
        for (PaafyldningsRegistrering paafyldningsRegistrering : paafyldningsRegistreringerFra){
            Paafyldningsvaeske paafyldningsvaeske = paafyldningsRegistrering.getPaafyldningsvaeske();
            if (paafyldningsvaeske instanceof FadIndhold){
                fade.addAll(((FadIndhold) paafyldningsvaeske).getFade());
            }
        }
        return fade;
    }

    @Override
    public double getAntalLiter() {
        initierModningsregistreringHvisIngen();
        return modningsRegistreringer.getLast().getAntalLiter();
    }



    public void reducerResterendeLiter(double antalLiter) {
        if (antalLiter <= 0) {
            throw new IllegalArgumentException("Antal liter skal være større end 0");
        }
        if (antalLiter> getAntalLiter()){
            throw new IllegalArgumentException("Der er kun " + getAntalLiter() + " tilbage, og du vil gerne bruge " + antalLiter);
        }
        initierModningsregistreringHvisIngen();

        modningsRegistreringer.getLast().reducerLiter(antalLiter);

    }

    /*public void setFad(Fad fad) {
        if (this.fad!=fad){
            fad.setFadIndhold(this);
            this.fad = fad;
        }
    }*/

    public ArrayList<PaafyldningsRegistrering> getPaafyldningsRegistreringerFra() {
        return new ArrayList<>(paafyldningsRegistreringerFra);
    }

    public ArrayList<ModningsRegistrering> getModningsRegistreringer() {
        initierModningsregistreringHvisIngen();
        return new ArrayList<>(modningsRegistreringer);
    }

    public void initierModningsregistreringHvisIngen(){
        if (modningsRegistreringer.isEmpty()){
            opretModningsRegistrering(beregnStartAlkoholProcent(),LocalDate.now(),beregnStartAntalLiter(),"Automatisk oprettet modningsregistrering ud fra påfyldt destilat","Autogenereret modningsregistrering");
        }
    }


    public double getSamletPaafyldning() {
        double samlet = 0;

        for (PaafyldningsRegistrering registrering : paafyldningsRegistreringerFra) {
            samlet += registrering.getAntalLiter();
        }
        return samlet;
    }


    public String toString() {
        if (!getModningsRegistreringer().isEmpty()) {
            return "Fad ID: " + fad.getFadId() +
                    " | Antal liter: " + getModningsRegistreringer().getLast().getAntalLiter() +
                    " | Alkoholprocent: " + String.format("%.2f", getModningsRegistreringer().getLast().getAlkoholProcent()) ;

        }
        return "Hej";
    }

    @Override
    public double getAlkoholProcent() {
        initierModningsregistreringHvisIngen();
        return modningsRegistreringer.getLast().getAlkoholProcent();
    }


    @Override
    public void addPaafyldningsRegistreringTil(PaafyldningsRegistrering paafyldningsRegistrering) {
        if (!paafyldningsRegistreringerTil.contains(paafyldningsRegistrering)){
            paafyldningsRegistreringerTil.add(paafyldningsRegistrering);
            paafyldningsRegistrering.setPaafyldningsvaeske(this);
        }
    }

    @Override
    public ArrayList<Destillering> getDestilleringer() {
        ArrayList<Destillering> destilleringer = new ArrayList<>();
        for (PaafyldningsRegistrering paafyldningsRegistrering : paafyldningsRegistreringerFra){
            Paafyldningsvaeske paafyldningsvaeske = paafyldningsRegistrering.getPaafyldningsvaeske();
            destilleringer.addAll(paafyldningsvaeske.getDestilleringer());
        }
        return destilleringer;
    }

    @Override
    public ArrayList<PaafyldningsRegistrering> getPaafyldningsRegistreringerTil() {
        return paafyldningsRegistreringerTil;
    }
}
