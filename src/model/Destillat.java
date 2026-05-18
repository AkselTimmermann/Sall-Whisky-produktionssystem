package model;

import java.util.ArrayList;

public class Destillat implements Paafyldningsvaeske {
    private double antalLiter;
    private double alkoholProcent;
    private String destillatNr;
    private ArrayList<PaafyldningsRegistrering> paafyldningsRegistreringerTil = new ArrayList<>();
    private ArrayList<Destillering> destilleringer = new ArrayList<>();

    public Destillat(String destillatNr, ArrayList<Destillering> destilleringer, double[] antalLiterAfHverDestillering) {
        if (destilleringer.size()!=antalLiterAfHverDestillering.length){
            throw new IllegalArgumentException("Hver brugt destillering skal have angivet en mængde");
        }
        this.destillatNr = destillatNr;
        setDestilleringer(destilleringer);
        for (int i = 0; i < destilleringer.size(); i++) {
            destilleringer.get(i).reducerResterendeLiter(antalLiterAfHverDestillering[i]);
            updateAntalLiterAndAlkoholprocent(antalLiterAfHverDestillering[i],destilleringer.get(i).getAlkoholProcent());
        }
    }

    private void setDestilleringer(ArrayList<Destillering> destilleringer) {
        destilleringer.forEach(destillering -> addDestillering(destillering));
    }

    public void addDestillering(Destillering destillering){
        if (!destilleringer.contains(destillering)){
            destilleringer.add(destillering);
            destillering.addDestillat(this);
        }
    }

    private void updateAntalLiterAndAlkoholprocent(double maengde, double alkoholProcent){
        this.alkoholProcent = (this.antalLiter*this.alkoholProcent+maengde*alkoholProcent)/(this.antalLiter+maengde);
        this.antalLiter+=maengde;
    }

    public double getAntalLiter() {
        return antalLiter;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    @Override
    public void addPaafyldningsRegistreringTil(PaafyldningsRegistrering paafyldningsRegistrering) {
        if (!paafyldningsRegistreringerTil.contains(paafyldningsRegistrering)){
            paafyldningsRegistreringerTil.add(paafyldningsRegistrering);
            paafyldningsRegistrering.setPaafyldningsvaeske(this);
        }
    }

    public String getDestillatNr() {
        return destillatNr;
    }

    @Override
    public ArrayList<PaafyldningsRegistrering> getPaafyldningsRegistreringerTil() {
        return new ArrayList<>(paafyldningsRegistreringerTil);
    }


    @Override
    public ArrayList<Destillering> getDestilleringer() {
        return new ArrayList<>(destilleringer);
    }

    public void reducerResterendeLiter(double liter) {
        if (liter > antalLiter) {
            throw new IllegalArgumentException("Ikke nok væske");
        }
        this.antalLiter -= liter;
    }



    @Override
    public String toString() {
        return destillatNr + " (" + antalLiter + " liter, " + String.format("%.2f", alkoholProcent) + "%)";
    }
}
