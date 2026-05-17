package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class WhiskyProdukt {
    private int produktNr;
    private String navn;
    private String beskrivelse;
    private LocalDate dato;
    private double fortynding;
    private ArrayList<Flaske> flasker = new ArrayList<>();
    private ArrayList<ProduktRegistrering> produktRegistreringer = new ArrayList<>();

    public WhiskyProdukt(String navn, int produktNr, String beskrivelse, LocalDate dato, double fortynding) {
        this.navn = navn;
        this.produktNr = produktNr;
        this.beskrivelse = beskrivelse;
        this.dato = dato;
        this.fortynding = fortynding;
    }


    public ProduktRegistrering createProduktRegistrering(double antalLiter, FadIndhold fadIndhold) {
        if (antalLiter<=0){
            throw new IllegalArgumentException("Antal liter skal være positiv");
        }

        if (fadIndhold == null) {
            throw new IllegalArgumentException("Fadindhold skal vælges");
        }

        if (antalLiter > fadIndhold.getResterendeLiter()) {
            throw new IllegalArgumentException("Der er ikke nok liter tilbage på fadindholdet");
        }

        if (!fadIndhold.isLagretMinimum3Aar(this.dato)){
            throw new IllegalArgumentException("Alt indhold i produktet skal være mindst 3 år gammelt");
        }
        ProduktRegistrering produktRegistrering = new ProduktRegistrering(antalLiter, fadIndhold, this);

        produktRegistreringer.add(produktRegistrering);
        fadIndhold.reducerResterendeLiter(antalLiter);

        return produktRegistrering;
    }




    public ArrayList<Flaske> createFlasker(double stoerrelse, int antal, FlaskeSamling flaskeSamling){
        if (stoerrelse<=0){
            throw new IllegalArgumentException("Størrelsen på en flaske skal altid være et positivt tal");
        }
        if (antal<=0){
            throw new IllegalArgumentException("Der skal oprettes mindst 1 flaske");
        }
        ArrayList<Flaske> oprettedeFlasker = new ArrayList<>();
        int startFlaskenr = this.flasker.getLast().getFlaskeNr() +1;
        for (int i = startFlaskenr; i < startFlaskenr + antal ; i++) {
            Flaske flaske = new Flaske(i,stoerrelse,flaskeSamling, this);
            oprettedeFlasker.add(flaske);
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
        if (stoerrelse<=0){
            throw new IllegalArgumentException("Størrelsen på en flaske skal altid være et positivt tal");
        }
        return (int) ((samletAntalLiter()-antalLiterIFlasker())/stoerrelse);
    }

    public double beregnAlkoholProcent(){
        if (samletAntalLiter()<=0){
            throw new IllegalStateException("Alkoholprocenten kan ikke udregnet, da der ikke er tilføjet whisky til produktet");
        }
        return samletAlkoholMaengde()/samletAntalLiter();
    }

    public double samletAlkoholMaengde() {
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

    public double beregnWhiskyTilovers(double stoerrelse, int antalFlasker) {
        if (stoerrelse <= 0) {
            throw new RuntimeException("Størrelse skal være større end 0");
        }
        if (antalFlasker <= 0) {
            throw new RuntimeException("Antal flasker skal være større end 0");
        }
        //Den mængde der allerede er tappet
        double resterendeLitter = samletAntalLiter() - antalLiterIFlasker();
        //Den mængde der ønskes tappet nu
        return resterendeLitter - (stoerrelse * antalFlasker);
    }


    public ArrayList<ProduktRegistrering> getProduktRegistreringer() {
        return new ArrayList<>(produktRegistreringer);
    }

    public String toString() {
        return navn + ", " + samletAntalLiter();
    }


}
