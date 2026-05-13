package model;

public class ProduktRegistrering {
    private double antalLiter;
    private FadIndhold fadIndhold;
    private WhiskyProdukt whiskyProdukt;

    public ProduktRegistrering(double antalLiter, FadIndhold fadIndhold, WhiskyProdukt whiskyProdukt) {
        this.antalLiter = antalLiter;
        this.fadIndhold = fadIndhold;
        this.whiskyProdukt = whiskyProdukt;
    }

    public WhiskyProdukt getWhiskyProdukt() {
        return whiskyProdukt;
    }

    public FadIndhold getFadIndhold() {
        return fadIndhold;
    }

    public double getAntalLiter() {
        return antalLiter;
    }
}
