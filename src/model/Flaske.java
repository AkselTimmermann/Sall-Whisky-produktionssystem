package model;

public class Flaske {
    private int flaskeNr;
    private double stoerrelse;
    private FlaskeSamling flaskeSamling;

    public Flaske(int flaskeNr, double stoerrelse, FlaskeSamling flaskeSamling) {
        this.flaskeNr = flaskeNr;
        this.stoerrelse = stoerrelse;
        this.flaskeSamling = flaskeSamling;
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
