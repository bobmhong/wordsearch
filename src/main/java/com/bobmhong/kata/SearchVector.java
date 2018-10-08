package com.bobmhong.kata;
import java.util.ArrayList;
import java.util.regex.*;  

public class SearchVector {
    private ArrayList<Cell> cells;
    
    public SearchVector() {
        this.cells = new ArrayList<Cell>();
    }

    public void addCell(Cell c) {
        this.cells.add(c);
    }

    public Cell getCell(int cellIndex) {
        return cells.get(cellIndex);
    }

    public void addCell(String cellText, int x, int y){
        Cell c = new Cell(cellText, x, y);
        this.cells.add(c);
    }
    
    public String getMatchCoordinates(String searchWord)
    {
        StringBuilder bld = new StringBuilder();

        String vectorString = this.toString();

        Pattern p = Pattern.compile(searchWord);
        Matcher m = p.matcher(vectorString);  
        
        if (m.find()){
            bld.append(searchWord);
            bld.append( ": ");
            for (int i = m.start(); i < m.end(); i++ ) {

                if (i > m.start()) {
                    bld.append(",");
                }
                bld.append(this.cells.get(i).getCoordinate());
            }
        }

        return bld.toString();
    }

    public int size(){
        return cells.size();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Cell c : this.cells) {
            sb.append(c.toString());
        }
        return sb.toString();
    }

    public SearchVector getReverseSearchVector(){
        
        SearchVector svReverse = new SearchVector();
        
        for(int i = cells.size() - 1; i >= 0; i--) {
            Cell c = cells.get(i);
            svReverse.addCell(c);
        }
        return svReverse;
    }
    
}