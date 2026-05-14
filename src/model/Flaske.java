package model;

public class Flaske {
    private int flaskeNr;
    private double stoerrelse;
    private FlaskeSamling flaskeSamling;
    private WhiskyProdukt whiskyProdukt;

    public Flaske(int flaskeNr, double stoerrelse, FlaskeSamling flaskeSamling, WhiskyProdukt whiskyProdukt) {
        this.flaskeNr = flaskeNr;
        this.stoerrelse = stoerrelse;
        this.flaskeSamling = flaskeSamling;
        this.whiskyProdukt = whiskyProdukt;
    }

    public void setFlaskeSamling(FlaskeSamling flaskeSamling) {
        if (this.flaskeSamling!=flaskeSamling){
            this.flaskeSamling = flaskeSamling;
            flaskeSamling.addFlaske(this);
        }
    }

    public int getFlaskeNr() {
        return flaskeNr;
    }

    public double getStoerrelse() {
        return stoerrelse;
    }

    public FlaskeSamling getFlaskeSamling() {
        return flaskeSamling;
    }
}
