package com.bobmhong.kata;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.BeforeClass;

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
        ArrayList<String> wsInputArrayList = ws.loadWordSearchInputArrayList(wsInputFileNameFullPath);
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
    public void testGetHorizontalSearchVectors() {
        Wordsearch ws = new Wordsearch();
        ArrayList<SearchVector> svList;
        
        ws.init(wsInputFileNameFullPath);

        svList = ws.getHorizontalSearchVectors();

        assertEquals(30, svList.size());
        String firstVectorString = "UMKHULKINVJOCWE";
        String lastVectorString =  "BAEKCFDMPQQBLYK";

        assertEquals(firstVectorString, svList.get(0).toString());
        assertEquals(lastVectorString, svList.get(29).toString());
    }

    @Test
    public void testGetVerticalSearchVectors() {
        Wordsearch ws = new Wordsearch();
        ArrayList<SearchVector> svList;
        
        ws.init(wsInputFileNameFullPath);

        svList = ws.getVerticalSearchVectors();

        assertEquals(30, svList.size());
        String firstVectorString = "ULHBASBONESTOWK";
        String lastVectorString =  "BSHKDKSPZFDEGGE";

        assertEquals(firstVectorString, svList.get(0).toString());
        assertEquals(lastVectorString, svList.get(29).toString());
    }

    @Test
    public void testGetDiag45DegreeSearchVectors() {
        Wordsearch ws = new Wordsearch();
        ArrayList<SearchVector> svList;
        
        ws.init(wsInputFileNameFullPath);

        svList = ws.getDiag45DegreeSearchVectors();
        int svListSize = svList.size();

        String firstVectorString = "LM";
        String firstVectorRevString = "ML";
        String lastVectorString =  "AS";
        String lastVectorRevString =  "SA";

        assertEquals(firstVectorString, svList.get(0).toString());
        assertEquals(firstVectorRevString, svList.get(1).toString());
        assertEquals(lastVectorString, svList.get(svListSize - 2).toString());
        assertEquals(lastVectorRevString, svList.get(svListSize - 1).toString());
    }

    @Test
    public void testGetDiag315DegreeSearchVectors() {
        Wordsearch ws = new Wordsearch();
        ArrayList<SearchVector> svList;
        
        ws.init(wsInputFileNameFullPath);

        svList = ws.getDiag315DegreeSearchVectors();
        int svListSize = svList.size();

        String firstVectorString = "GW";
        String firstVectorRevString = "WG";
        String lastVectorString =  "YW";
        String lastVectorRevString =  "WY";

        assertEquals(firstVectorString, svList.get(0).toString());
        assertEquals(firstVectorRevString, svList.get(1).toString());
        assertEquals(lastVectorString, svList.get(svListSize - 2).toString());
        assertEquals(lastVectorRevString, svList.get(svListSize - 1).toString());
    }
}
