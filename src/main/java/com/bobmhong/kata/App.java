package com.bobmhong.kata;

public class App {
	public static void main(String[] args) {
		String wsInputFileNameFullPath = "";

		if (args.length > 0) {
			wsInputFileNameFullPath = args[0];
		} else {
			System.out.println("Please specify the path to your Word Search data file as the first command line parameter.");
			System.exit(1);	
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
