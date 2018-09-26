package com.bobmhong.kata;

import java.io.IOException;
import java.util.ArrayList;

public class App 
{
    public static void main( String[] args )
    {
        
        String wsInputFileName = "trekSampleData.txt";
        ClassLoader classLoader = App.class.getClassLoader();
        String wsInputFileNameFullPath = classLoader.getResource(wsInputFileName).getFile();

        // Strip leading / in Windows which returns path like /c:/mypath
        if (wsInputFileNameFullPath.contains(":")) {
            wsInputFileNameFullPath = wsInputFileNameFullPath.substring(1);
        }
        Wordsearch ws = new Wordsearch();
        try {
            ArrayList<String> wsInputArrayList = ws.LoadWordSearchInputArrayList(wsInputFileNameFullPath);
            System.out.println( "Word List: " + wsInputArrayList.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
}
