package com.bobmhong.kata;

public class App 
{
    public static void main(String[] args) {

		String wsInputFileName = "trekSampleData.txt";
		ClassLoader classLoader = App.class.getClassLoader();
		String wsInputFileNameFullPath = classLoader.getResource(wsInputFileName).getFile();

		// Strip leading / in Windows which returns path like /c:/mypath
		if (wsInputFileNameFullPath.contains(":")) {
			wsInputFileNameFullPath = wsInputFileNameFullPath.substring(1);
		}
		Wordsearch ws = new Wordsearch();

		ws.init(wsInputFileNameFullPath);
		ws.populateSearchList();
		ws.solvePuzzle();

	}
    
}
