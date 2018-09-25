package com.bobmhong.kata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class wordsearch {
		
	public ArrayList<String> LoadWordSearchInputArrayList(String wsInputFileName) throws IOException {

		ArrayList<String> wordSearchInputArrayList;
			wordSearchInputArrayList = new ArrayList<String>(Files.readAllLines(Paths.get(wsInputFileName)));
			return wordSearchInputArrayList;

	}

	public static String[] parseWordList(String wordList) {
		return wordList.split(",");
	}

}