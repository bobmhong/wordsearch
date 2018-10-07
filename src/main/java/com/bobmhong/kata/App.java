package com.bobmhong.kata;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
	private static final Logger logger = LogManager.getLogger(com.bobmhong.kata.App.class);

	public static void main(String[] args) {
		String wsInputFileNameFullPath = "";

		if (args.length == 0) {
			String msg = "Please specify the path to your Word Search data file as the first command line parameter.";
			logger.error(msg);
		} else {
			wsInputFileNameFullPath = args[0];
			logger.info("Attempting to load: " + wsInputFileNameFullPath);

			try {
				Wordsearch ws = new Wordsearch();

				ws.init(wsInputFileNameFullPath);
				if (ws.populateSearchList()) {
					ws.solvePuzzle();
				} else {
					logger.info("Sorry, No word matches were found.");
				}
			} catch (Exception e) {
				logger.fatal(e.getMessage());
			}
		}

	}

}
