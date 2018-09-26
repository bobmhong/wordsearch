package com.bobmhong.kata;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class WordsearchTest 
{
    @Test
    public void testLoadWordSearchInputArrayList() throws IOException {
        
        String wsInputFileName = "trekSampleData.txt";
        ClassLoader classLoader = WordsearchTest.class.getClassLoader();
        String wsInputFileNameFullPath = classLoader.getResource(wsInputFileName).getFile();
        
        // Strip leading / in Windows which returns path like /c:/mypath
        if (wsInputFileNameFullPath.contains(":")) {
            wsInputFileNameFullPath = wsInputFileNameFullPath.substring(1);
        }
        
        Wordsearch ws = new Wordsearch();
        ArrayList<String> wsInputArrayList = ws.LoadWordSearchInputArrayList(wsInputFileNameFullPath);
        assertEquals("BONES,KHAN,KIRK,SCOTTY,SPOCK,SULU,UHURA", wsInputArrayList.get(0));
    }

    @Test
    public void testParseWordlist(){

        ArrayList<String> sampleArrayList = new ArrayList<String>();
        sampleArrayList.add("firstword,secondword,thirdword");
        sampleArrayList.add("0,1,2");
        sampleArrayList.add("3,4,5");
        sampleArrayList.add("6,7,8");
        
        //Wordsearch ws = new Wordsearch();
        String[] wordList = Wordsearch.parseWordList(sampleArrayList);
        assertEquals("firstword", wordList[0]);
        assertEquals("thirdword", wordList[2]);

    }

}
