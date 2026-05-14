package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class WhiskyProdukt {
    private int produktNr;
    private String beskrivelse;
    private LocalDate dato;
    private double fortynding;
    private ArrayList<Flaske> flasker = new ArrayList<>();
    private ArrayList<ProduktRegistrering> produktRegistreringer = new ArrayList<>();

    public WhiskyProdukt(int produktNr, String beskrivelse, LocalDate dato, double fortynding) {
        this.produktNr = produktNr;
        this.beskrivelse = beskrivelse;
        this.dato = dato;
        this.fortynding = fortynding;
    }

    public ArrayList<Flaske> createFlasker(double stoerrelse, int antal, FlaskeSamling flaskeSamling){
        ArrayList<Flaske> oprettedeFlasker = new ArrayList<>();
        int startFlaskenr = this.flasker.getLast().getFlaskeNr() +1;
        for (int i = startFlaskenr; i < startFlaskenr + antal ; i++) {
            Flaske flaske = new Flaske(i,stoerrelse,flaskeSamling, this);
            oprettedeFlasker.add(flaske);
            flaske.setFlaskeSamling(flaskeSamling);
            this.flasker.add(flaske);

        }
        return oprettedeFlasker;
    }



    public boolean isCaskStrength(){
        return fortynding==0;
    }

    /*
    Returnerer om en whisky er single cask eller kun single malt.
    Lige nu er der ikke mulighed for andre typer, da Sall ikke mixer deres Whisky
    med andre producenters whisky og bruger kun Byg i deres produktion
     */
    public WhiskyType isWhiskyType(){
        Fad etFad = produktRegistreringer.getFirst().getFadIndhold().getFad();
        for (ProduktRegistrering produktRegistrering : produktRegistreringer){
            Fad andetFad = produktRegistrering.getFadIndhold().getFad();
            if (etFad != andetFad){
                return WhiskyType.SINGLEMALT;
            };
        }
        return WhiskyType.SINGLECASK;
    }

    public int maksAntalFlasker(double stoerrelse){
        return (int) ((samletAntalLiter()-antalLiterIFlasker())/stoerrelse);
    }

    public double beregnAlkoholProcent(){
        return samletAlkoholMaengde()/samletAntalLiter();
    }

    private double samletAlkoholMaengde() {
        double samletAlkoholMaengde=0;
        for (ProduktRegistrering produktRegistrering : produktRegistreringer){
            double antalLiter = produktRegistrering.getAntalLiter();
            // følgende finder alkoholprocenten ved sidste modningsregistrering, som skal være opdateret ved udregning.
            double alkoholProcent = produktRegistrering.getFadIndhold().getModningsRegistreringer().getLast().getAlkoholProcent();
            samletAlkoholMaengde+=antalLiter*alkoholProcent;
        }
        return samletAlkoholMaengde;
    }

    public double samletAntalLiter(){
        return produktRegistreringer.stream().mapToDouble(produktRegistrering -> produktRegistrering.getAntalLiter()).sum();
    }

    public double antalLiterIFlasker(){
        return flasker.stream().mapToDouble(flaske -> flaske.getStoerrelse()).sum();
    }

    public ArrayList<ProduktRegistrering> getProduktRegistreringer() {
        return new ArrayList<>(produktRegistreringer);
    }
}
