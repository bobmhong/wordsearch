package com.bobmhong.kata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Wordsearch {
		
	public ArrayList<String> LoadWordSearchInputArrayList(String wsInputFileName) throws IOException {
		// returns an ArrayList of Strings from the file specified as a parameter
		ArrayList<String> wordSearchInputArrayList;
			wordSearchInputArrayList = new ArrayList<String>(Files.readAllLines(Paths.get(wsInputFileName)));
			return wordSearchInputArrayList;

	}

	public static String[] parseWordList(ArrayList<String> wsArrayList) {
		// returns an array of Strings of the words to search for
		String wordList = wsArrayList.get(0);
		return wordList.split(",");
	}

	public static String[][] parseSearchGrid(ArrayList<String> wsArrayList){
		// return a 2-d char array 
		int maxidx = wsArrayList.get(0).split(",").length;

		String searchGrid[][] = new String[maxidx][maxidx];
		
		int i,j = 0;

		for (i=0; i<=maxidx-1; i++){
			String dataRow = wsArrayList.get(i+1);
			String[] letterArray = dataRow.split(",");
			for (j=0; j<=maxidx-1; j++){
				searchGrid[i][j] = letterArray[j];
			}
		}
		return searchGrid;
	}



}