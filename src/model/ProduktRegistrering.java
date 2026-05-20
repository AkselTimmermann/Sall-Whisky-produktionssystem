package model;

public class ProduktRegistrering {
    private double antalLiter;
    private FadIndhold fadIndhold;
    private WhiskyProdukt whiskyProdukt;

    public ProduktRegistrering(double antalLiter, FadIndhold fadIndhold, WhiskyProdukt whiskyProdukt) {
        this.antalLiter = antalLiter;
        setFadindhold(fadIndhold);
        this.whiskyProdukt = whiskyProdukt;

    }

    public void setFadindhold(FadIndhold fadIndhold) {
        if (!this.fadIndhold.equals(fadIndhold)){
            this.fadIndhold = fadIndhold;
            fadIndhold.addProduktRegistrering(this);
        }
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
