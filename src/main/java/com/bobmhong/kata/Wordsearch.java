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

	public void buildSearchList() {

	}

	public void solvePuzzle() {


		for (String word : wordList) {
			for (SearchVector sv : searchList) {
				String matchCoordinates = sv.getMatchCoordinates(word);
				if (matchCoordinates != "") {
					System.out.println(matchCoordinates);
				}
			}
		}

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
		searchList.addAll(getHorizontalReverseSearchVectors());

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