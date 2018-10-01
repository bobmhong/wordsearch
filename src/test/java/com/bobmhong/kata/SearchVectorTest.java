package com.bobmhong.kata;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SearchVectorTest{
    @Test
    public void testAddCell() {
        SearchVector sv = new SearchVector();
        sv.addCell("A", 1, 0);
        assertEquals(1, sv.size());
    }

    @Test
    public void testGetMatchCoordinates() {

        String searchWord = "DOG";

        SearchVector sv = new SearchVector();
        sv.addCell("A", 1, 0);
        sv.addCell("S", 2, 0);
        sv.addCell("X", 3, 0);
        sv.addCell("D", 4, 0);
        sv.addCell("O", 5, 0);
        sv.addCell("G", 6, 0);
        sv.addCell("P", 7, 0);

        String coordString = sv.getMatchCoordinates(searchWord);

        assertEquals("DOG: (4,0),(5,0),(6,0)", coordString);

    }

    @Test
    public void testToString() {

        SearchVector sv = new SearchVector();
        sv.addCell("A", 1, 0);
        sv.addCell("S", 2, 0);
        sv.addCell("X", 3, 0);

        String vectorString = sv.toString();

        assertEquals("ASX", vectorString);

    }
}