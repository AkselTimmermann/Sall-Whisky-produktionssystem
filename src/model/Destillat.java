package model;

import java.util.ArrayList;

public class Destillat {
    private double antalLiter;
    private double alkoholProcent;
    private String destillatNr;
    private ArrayList<PaafyldningsRegistrering> paafyldningsRegistreringer = new ArrayList<>();
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
        return antalLiter - paafyldningsRegistreringer.stream().mapToDouble(paafyldningsRegistrering->paafyldningsRegistrering.getAntalLiter()).sum();
    }

    public double getOprindeligAntalLiter(){
        return antalLiter;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public String getDestillatNr() {
        return destillatNr;
    }

    public ArrayList<PaafyldningsRegistrering> getPaafyldningsRegistreringer() {
        return new ArrayList<>(paafyldningsRegistreringer);
    }

    public ArrayList<Destillering> getDestilleringer() {
        return new ArrayList<>(destilleringer);
    }

    public void tjekLiterNok(double liter) {
        if (liter > getAntalLiter()) {
            throw new IllegalArgumentException("Ikke nok væske");
        }
    }

    @Override
    public String toString() {
        return destillatNr + " (" + antalLiter + " liter, " + String.format("%.2f", alkoholProcent) + "%)";
    }

    public void addPaafyldningsregistrering(PaafyldningsRegistrering paafyldningsRegistrering) {
        if (!paafyldningsRegistreringer.contains(paafyldningsRegistrering)){
            this.paafyldningsRegistreringer.add(paafyldningsRegistrering);
            paafyldningsRegistrering.setDestillat(this);
        }
    }
}
