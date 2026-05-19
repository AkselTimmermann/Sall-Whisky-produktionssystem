package model;

import java.util.ArrayList;

public class MaltBatch {
    private String kornMark;
    private String bygSort;
    private String batchNr;

    public MaltBatch(String kornMark, String bygSort, String batchNr) {
        this.kornMark = kornMark;
        this.bygSort = bygSort;
        this.batchNr = batchNr;
    }

    public String toString() {
        return "Mark: " + kornMark + " | " + " Korn sort" + bygSort + "\n";
    }

}
