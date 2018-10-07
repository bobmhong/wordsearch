package com.bobmhong.kata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Wordsearch {
	private String[] wordList;
	private String[][] searchGrid;
	private ArrayList<SearchVector> searchList;
	
	private static final Logger logger = LogManager.getLogger(com.bobmhong.kata.App.class);

	public Wordsearch() {
		searchList = new ArrayList<SearchVector>();
	}

	public String[] getWordList() {
		return wordList;
	}

	public String[][] getSearchGrid() {
		return searchGrid;
	}

	ArrayList<String> loadWordSearchInputArrayList(String wsInputFileName) throws IOException {
		// returns an ArrayList of Strings from the file specified as a parameter
		ArrayList<String> wordSearchInputArrayList;
		wordSearchInputArrayList = new ArrayList<String>(Files.readAllLines(Paths.get(wsInputFileName)));
		return wordSearchInputArrayList;

	}

	static String[] parseWordList(ArrayList<String> wsArrayList) {
		// returns an array of Strings of the words to search for
		String wordList = wsArrayList.get(0);
		return wordList.split(",");
	}

	static String[][] parseSearchGrid(ArrayList<String> wsArrayList) {
		// return a 2-d char array
		// length and width of letter grid are equal so
		// get size from the second element of the input array list.
		// (The first row is the word list to search for.)
		int maxidx = wsArrayList.get(1).split(",").length;

		String searchGrid[][] = new String[maxidx][maxidx];

		int x, y = 0;

		for (y = 0; y < maxidx; y++) {
			String dataRow = wsArrayList.get(y + 1);
			String[] letterArray = dataRow.split(",");
			for (x = 0; x < maxidx; x++) {
				searchGrid[x][y] = letterArray[x];
			}
		}
		return searchGrid;
	}

	public void buildSearchList() {

	}

	public void solvePuzzle() {

		for (String word : wordList) {
			for (SearchVector sv : searchList) {
				String matchCoordinates = sv.getMatchCoordinates(word);
				if (matchCoordinates != "") {
					logger.info(matchCoordinates);
				}
			}
		}

	}

	public boolean init(String wsInputFileNameFullPath) {
		ArrayList<String> inputArrayList;

		try {
			inputArrayList = LoadWordSearchInputArrayList(wsInputFileNameFullPath);
		} catch (IOException e) {
			logger.error("Error loading Word Search Input File: " + wsInputFileNameFullPath, e);
			return false;
		}

		wordList = parseWordList(inputArrayList);
		searchGrid = parseSearchGrid(inputArrayList);
		return true;

	}

	boolean populateSearchList() {
		// traverse the search grid to gather all possible search vectors

		boolean result = false;

		searchList.addAll(getHorizontalSearchVectors());
		searchList.addAll(getVerticalSearchVectors());
		searchList.addAll(getDiag45DegreeSearchVectors());
		searchList.addAll(getDiag315DegreeSearchVectors());

		if (this.searchList.size() > 0) {
			result = true;
		}
		return result;
	}

	ArrayList<SearchVector> getHorizontalSearchVectors() {
		ArrayList<SearchVector> svList = new ArrayList<SearchVector>();
		int x, y;

		for (y = 0; y < searchGrid[0].length; y++) {
			SearchVector sv = new SearchVector();
			for (x = 0; x < searchGrid[1].length; x++) {
				sv.addCell(searchGrid[x][y], x, y);
			}
			svList.add(sv);
			svList.add(sv.getReverseSearchVector());
		}

		return svList;
	}
	ArrayList<SearchVector> getVerticalSearchVectors() {
		ArrayList<SearchVector> svList = new ArrayList<SearchVector>();
		int x, y;

		for (x = 0; x < searchGrid[0].length; x++) {
			SearchVector sv = new SearchVector();
			for (y = 0; y < searchGrid[1].length; y++) {
				sv.addCell(searchGrid[x][y], x, y);
			}
			svList.add(sv);
			svList.add(sv.getReverseSearchVector());
		}

		return svList;
	}

	ArrayList<SearchVector> getDiag45DegreeSearchVectors() {
		ArrayList<SearchVector> svList = new ArrayList<SearchVector>();
		int startX, startY, x, y;
		int gridSize = searchGrid[0].length;
		SearchVector sv; 
		
		startX = 0;
		startY = 1;
		x = startX;
		y = startY;
		while (y < gridSize) {
			sv = new SearchVector();
			while (y >= 0) {
				sv.addCell(searchGrid[x][y], x, y);
				x++;
				y--;
			}
			svList.add(sv);
			svList.add(sv.getReverseSearchVector());
			startX = 0;
			startY += 1;
			x = startX;
			y = startY;
		}

		startX = 1;
		startY = gridSize - 1;
		x = startX;
		y = startY;
		while (x < gridSize - 1) {
			sv = new SearchVector();
			while (x < gridSize) {
				sv.addCell(searchGrid[x][y], x, y);
				x++;
				y--;
			}
			svList.add(sv);
			svList.add(sv.getReverseSearchVector());
			startX++;
			startY = gridSize - 1;
			x = startX;
			y = startY;
		}
		
		return svList;
	}

	ArrayList<SearchVector> getDiag315DegreeSearchVectors() {
		ArrayList<SearchVector> svList = new ArrayList<SearchVector>();
		int startX, startY, x, y;
		int gridSize = searchGrid[0].length;
		SearchVector sv; 
		
		startX = gridSize - 1;
		startY = 1;
		x = startX;
		y = startY;
		while (y < gridSize) {
			sv = new SearchVector();
			while (y >= 0) {
				sv.addCell(searchGrid[x][y], x, y);
				x--;
				y--;
			}
			svList.add(sv);
			svList.add(sv.getReverseSearchVector());
			startX = gridSize - 1;
			startY += 1;
			x = startX;
			y = startY;
		}

		startX = gridSize - 2;
		startY = gridSize - 1;
		x = startX;
		y = startY;
		while (x >= 1) {
			sv = new SearchVector();
			while (x >= 0 ) {
				sv.addCell(searchGrid[x][y], x, y);
				x--;
				y--;
			}
			svList.add(sv);
			svList.add(sv.getReverseSearchVector());
			startX--;
			startY = gridSize - 1;
			x = startX;
			y = startY;
		}
		
		return svList;
	}
}