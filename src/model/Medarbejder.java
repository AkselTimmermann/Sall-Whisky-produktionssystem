package model;

public class Medarbejder {
    private String navn;
    private int medArbejderNr;

    public Medarbejder(String navn, int medArbejderNr) {
        this.navn = navn;
        this.medArbejderNr = medArbejderNr;
    }


    @Override
    public String toString() {
        return navn + " (medarbejdernr.: " + medArbejderNr + ")";
    }
}
