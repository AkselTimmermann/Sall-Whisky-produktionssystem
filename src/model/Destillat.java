package model;

import java.util.ArrayList;

public class Destillat {
    private int antalLiter;
    private double alkoholProcent;
    private String destillatNr;
    private ArrayList<PaafyldningsRegistrering> paafyldningsRegistreringer = new ArrayList<>();
    private ArrayList<Destillering> destilleringer = new ArrayList<>();

    public Destillat(String destillatNr, ArrayList<Destillering> destilleringer, int[] antalLiterAfHverDestillering) {
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

    private void updateAntalLiterAndAlkoholprocent(int maengde, double alkoholProcent){
        this.alkoholProcent = (this.antalLiter*this.alkoholProcent+maengde*alkoholProcent)/(this.antalLiter+maengde);
        this.antalLiter+=maengde;
    }

    public int getAntalLiter() {
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


    @Override
    public String toString() {
        return destillatNr + " (" + antalLiter + " liter, " + String.format("%.2f", alkoholProcent) + "%)";
    }
}
