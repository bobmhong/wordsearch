package com.bobmhong.kata;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Ignore;

public class WordsearchTest {
    static String wsInputFileNameFullPath;

     @BeforeClass public static void resolveTestFile() {
        String wsInputFileName = "trekSampleData.txt";
        ClassLoader classLoader = WordsearchTest.class.getClassLoader();
        wsInputFileNameFullPath = classLoader.getResource(wsInputFileName).getFile();

        // Strip leading / in Windows which returns path like /c:/mypath
        if (wsInputFileNameFullPath.contains(":")) {
            wsInputFileNameFullPath = wsInputFileNameFullPath.substring(1);
        }
        System.out.println("Input Filename Full Path:" + wsInputFileNameFullPath);
    } 

    @Test
    public void testLoadWordSearchInputArrayList() throws IOException {

        Wordsearch ws = new Wordsearch();
        ArrayList<String> wsInputArrayList = ws.LoadWordSearchInputArrayList(wsInputFileNameFullPath);
        assertEquals("BONES,KHAN,KIRK,SCOTTY,SPOCK,SULU,UHURA", wsInputArrayList.get(0));
    }

    @Test
    public void testParseWordlist() {

        ArrayList<String> sampleArrayList = new ArrayList<String>();
        sampleArrayList.add("firstword,secondword,thirdword");
        sampleArrayList.add("0,1,2");
        sampleArrayList.add("3,4,5");
        sampleArrayList.add("6,7,8");

        // Wordsearch ws = new Wordsearch();
        String[] wordList = Wordsearch.parseWordList(sampleArrayList);
        assertEquals("firstword", wordList[0]);
        assertEquals("thirdword", wordList[2]);

    }

    @Test
    public void testParseSearchGrid() {

        ArrayList<String> sampleArrayList = new ArrayList<String>();
        sampleArrayList.add("firstword,secondword,thirdword");
        sampleArrayList.add("0,1,2");
        sampleArrayList.add("3,4,5");
        sampleArrayList.add("6,7,8");

        // Wordsearch ws = new Wordsearch();
        String[][] searchGrid = Wordsearch.parseSearchGrid(sampleArrayList);
        assertEquals("0", searchGrid[0][0]);
        assertEquals("6", searchGrid[0][2]);
        assertEquals("2", searchGrid[2][0]);
        assertEquals("8", searchGrid[2][2]);
    }

    @Ignore("implement helper methods first")
    @Test
    public void testSolveHorizontalForward() {

        String[] wordList = { "BIRD", "CAT", "DOG", "FISH", "MONKEY", "PIG", "RAT", "SNAKE", "TURTLE" };

        String[][] searchGrid = { 
            { "E", "L", "T", "R", "U", "T" }, 
            { "E", "K", "A", "N", "S", "A" },
            { "H", "S", "I", "F", "G", "C" }, 
            { "M", "O", "N", "K", "E", "Y" }, 
            { "T", "A", "R", "P", "I", "G" },
            { "B", "I", "R", "D", "O", "G" } 
        };

        // Wordsearch ws = new Wordsearch();
        ArrayList<String> solutionArrayList = Wordsearch.solveHorizontalForward(wordList, searchGrid);
        assertEquals("MONKEY: (3,0), (3,1), (3,2), (3,3), (3,4), (3,5)", solutionArrayList.get(0));
        assertEquals("PIG: (4,3), (4,4), (4,5)", solutionArrayList.get(1));
        assertEquals("BIRD: (5,0), (5,1), (5,2)", solutionArrayList.get(2));
        assertEquals("DOG: (5,3), (5,4), (5,5)", solutionArrayList.get(2));
    }

    @Test
    public void testInitGoodInputFileName(){
        Wordsearch ws = new Wordsearch();
		
        boolean result = ws.init(wsInputFileNameFullPath);
        assertEquals(true, result);
        
        String[] wl = ws.getWordList();
        String[][] sg = ws.getSearchGrid();

        assertEquals(7, wl.length);
        assertEquals(15, sg[0].length);
        assertEquals(15, sg[1].length);
    }
    
    @Test
    public void testInitBadInputFileName(){
        Wordsearch ws = new Wordsearch();
		
        boolean result = ws.init("/resource/nonexistentFile.txt");
        assertEquals(false, result);
    }

    @Test
    public void testGetHorizontalForwardSearchVectors() {
        Wordsearch ws = new Wordsearch();
        ArrayList<SearchVector> svList;
        
        ws.init(wsInputFileNameFullPath);

        svList = ws.getHorizontalForwardSearchVectors();

        assertEquals(15, svList.size());
        String firstVectorString = "UMKHULKINVJOCWE";
        String lastVectorString = "KYLBQQPMDFCKEAB";

        assertEquals(firstVectorString, svList.get(0).toString());
        assertEquals(lastVectorString, svList.get(14).toString());
    }

    @Test
    public void testGetHorizontalReverseSearchVectors() {
        Wordsearch ws = new Wordsearch();
        ArrayList<SearchVector> svList;
        
        ws.init(wsInputFileNameFullPath);

        svList = ws.getHorizontalReverseSearchVectors();

        assertEquals(15, svList.size());
        String firstVectorString = "EWCOJVNIKLUHKMU";
        String lastVectorString =  "BAEKCFDMPQQBLYK";

        assertEquals(firstVectorString, svList.get(0).toString());
        assertEquals(lastVectorString, svList.get(14).toString());
    }
}
