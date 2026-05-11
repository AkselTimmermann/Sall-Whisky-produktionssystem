package model;

import java.util.ArrayList;

public class MaltBatch {
    private String kornMark;
    private String bygSort;
    private String batchNr;
    private ArrayList<Destillering> destilleringer = new ArrayList<>();

    public MaltBatch(String kornMark, String bygSort, String batchNr) {
        this.kornMark = kornMark;
        this.bygSort = bygSort;
        this.batchNr = batchNr;
    }

    public void addDestillering (Destillering destillering){
        if (!destilleringer.contains(destillering)){
            destilleringer.add(destillering);
            destillering.setMaltBatch(this);
        }
    }


}
