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



}
