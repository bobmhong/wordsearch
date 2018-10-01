package com.bobmhong.kata;
import java.util.ArrayList;
import java.util.regex.*;  

public class SearchVector {
    private ArrayList<Cell> cells;
    
    public SearchVector() {
        this.cells = new ArrayList<Cell>();
    }

    public void addCell(String cellText, int x, int y){
        Cell c = new Cell(cellText, x, y);
        this.cells.add(c);
    }
    
    public String getMatchCoordinates(String searchWord)
    {
        String coordString = "";

        String vectorString = this.toString();

        Pattern p = Pattern.compile(searchWord);
        Matcher m = p.matcher(vectorString);  
        
        if (m.find()){
            coordString = searchWord + ": ";
            for (int i = m.start(); i < m.end(); i++ ) {

                if (i > m.start()) {
                    coordString += ",";
                }

                coordString += this.cells.get(i).getCoordinate();
            }
        }

        return coordString;
    }

    public int size(){
        return cells.size();
    }

    @Override
    public String toString(){
        String vectorString = "";
        for (Cell c : this.cells) {
            vectorString += c.toString();
        }
        return vectorString;
    }
    
}