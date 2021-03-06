package com.bobmhong.kata;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CellTest{
@Test
    public void testGetCellCoordinate() {
        Cell c = new Cell("A", 1, 0);
        assertEquals("(1,0)", c.getCoordinate());
    }
    
    @Test
    public void testGetCellString() {
        Cell c = new Cell("A", 1, 0);
        assertEquals("A", c.toString());
    }

    @Test
    public void testAddCell() {
        Cell c = new Cell("A", 1, 0);
        assertEquals("A", c.toString());
    }
}
