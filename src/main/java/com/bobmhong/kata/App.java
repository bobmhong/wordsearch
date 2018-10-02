package com.bobmhong.kata;

public class App {
	public static void main(String[] args) {
		String wsInputFileName;
		String wsInputFileNameFullPath;

		if (args.length > 0) {
			wsInputFileNameFullPath = args[0];
		} else {
			// use sample file if none is specified
			wsInputFileName = "trekSampleData.txt";
			ClassLoader classLoader = App.class.getClassLoader();
			wsInputFileNameFullPath = classLoader.getResource(wsInputFileName).getFile();

			System.out.println("Original File Attempting to load: " + wsInputFileNameFullPath);

			// Strip leading / in Windows which returns path like /c:/mypath
			if (wsInputFileNameFullPath.contains(":")) {
				wsInputFileNameFullPath = wsInputFileNameFullPath.substring(1);
			}

		}

		System.out.println("Attempting to load: " + wsInputFileNameFullPath);

		Wordsearch ws = new Wordsearch();

		ws.init(wsInputFileNameFullPath);
		if (ws.populateSearchList()) {
			ws.solvePuzzle();
		} else {
			System.out.println("Sorry, No word matches were found.");
		}

	}

}
