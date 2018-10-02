package com.bobmhong.kata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Wordsearch {
	private String[] wordList;
	private String[][] searchGrid;
	private ArrayList<SearchVector> searchList;

	public Wordsearch() {

	}

	public String[] getWordList() {
		return wordList;
	}

	public String[][] getSearchGrid() {
		return searchGrid;
	}

	ArrayList<String> LoadWordSearchInputArrayList(String wsInputFileName) throws IOException {
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

	static ArrayList<String> solveHorizontalForward(String[] wordList, String[][] searchGrid) {
		ArrayList<String> solutionArrayList = new ArrayList<String>();

		int gridSize = searchGrid.length;
		int i, j = 0;

		for (String word : wordList) {
			for (i = 0; i < gridSize - 1; i++) {
				boolean foundWord = false;
				String nextLetter = word.substring(0, 1);
				for (j = 0; j <= gridSize - 1; j++) {
					if (searchGrid[i][j] == nextLetter) {
						// Save coordinates

						// If Word Fully Matched then persist word and coordinates and break out to
						// search for next word

						// else save temp coordinates and set next letter
					} else {
						// reset temp coordinate list
					}
				}

			}
		}
		return solutionArrayList;
	}

	public void buildSearchList() {

	}

	public void solvePuzzle() {

	}

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

	}

	public boolean init(String wsInputFileNameFullPath) {
		ArrayList<String> inputArrayList;

		try {
			inputArrayList = LoadWordSearchInputArrayList(wsInputFileNameFullPath);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		wordList = parseWordList(inputArrayList);
		searchGrid = parseSearchGrid(inputArrayList);
		return true;

	}

	boolean populateSearchList() {
		// traverse the search grid to gather all possible search vectors

		boolean result = false;

		searchList.addAll(getHorizontalForwardSearchVectors());

		if (this.searchList.size() > 0) {
			result = true;
		}
		return result;
	}

	ArrayList<SearchVector> getHorizontalForwardSearchVectors() {
		ArrayList<SearchVector> svList = new ArrayList<SearchVector>();
		int x, y;

		for (y = 0; y < searchGrid[0].length; y++) {
			SearchVector sv = new SearchVector();
			for (x = 0; x < searchGrid[1].length; x++) {
				sv.addCell(searchGrid[x][y], x, y);
			}
			svList.add(sv);
		}

		return svList;
	}

	ArrayList<SearchVector> getHorizontalReverseSearchVectors() {
		ArrayList<SearchVector> svList = new ArrayList<SearchVector>();
		int x, y;

		for (y = 0; y < searchGrid[0].length; y++) {
			SearchVector sv = new SearchVector();
			for (x = searchGrid[1].length - 1; x >= 0; x--) {
				sv.addCell(searchGrid[x][y], x, y);
			}
			svList.add(sv);
		}

		return svList;
	}
}