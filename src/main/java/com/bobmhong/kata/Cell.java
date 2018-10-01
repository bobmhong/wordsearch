package com.bobmhong.kata;

public class Cell {
    private int x;
    private int y;
    private String cellText;

    public Cell(String cellText, int x, int y) {
        this.cellText = cellText;
        this.x = x;
        this.y = y;
    }

    public String getCoordinate() {
        return "(" + this.x + "," + this.y + ")";
    }

    @Override
    public String toString(){
        return this.cellText;
    }
}