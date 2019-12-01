package org.computermentors.golfscorecard;

public class Hole {
    private String lable;
    private int strokeCount;

    public Hole(String label, int strokeCount){
         this.lable = label;
         this.strokeCount = strokeCount;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public int getStrokeCount() {
        return strokeCount;
    }

    public void setStrokeCount(int strokeCount) {
        this.strokeCount = strokeCount;
    }
}
