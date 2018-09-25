package com.bobmhong.kata;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class wordsearchTest 
{
    @Test
    public void testLoadWordSearchInputArrayList() throws IOException {
        
        String wsInputFileName = "trekSampleData.txt";
        ClassLoader classLoader = wordsearchTest.class.getClassLoader();
        String wsInputFileNameFullPath = classLoader.getResource(wsInputFileName).getFile();
        
        if (wsInputFileNameFullPath.contains(":")) {
            wsInputFileNameFullPath = wsInputFileNameFullPath.substring(1);
        }
        
        wordsearch ws = new wordsearch();
        ArrayList<String> wsInputArrayList = ws.LoadWordSearchInputArrayList(wsInputFileNameFullPath);
        assertEquals("BONES,KHAN,KIRK,SCOTTY,SPOCK,SULU,UHURA", wsInputArrayList.get(0));
    }

}
