package textdecorators.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import textdecorators.exception.TextDecoratorException;

/**
 * @author Madhan Thangavel
 */

/*
 * FileProcessor is a utility to be used to read in the contents of the input
 * file.
 */
public final class FileProcessor {

	private BufferedReader reader;

	/**
	 * To initialte the input files.
	 * 
	 * @param inputFile
	 * @return boolean
	 * @throws TextDecoratorException On error encountered when handling the input.
	 */
	public boolean initiateInput(String inputFile) throws TextDecoratorException {

		try {
			reader = new BufferedReader(new FileReader(new File(inputFile)));
			return true;
		} catch (FileNotFoundException e) {
			throw new TextDecoratorException("File doesn't exist - " + inputFile);
		}

	}

	/**
	 * Retrieves and returns the next line in the input file.
	 *
	 * @return String The next line read from the input file.
	 */
	public String poll() throws IOException {
		return reader.readLine();
	}

	/**
	 * Close the buffered reader instance.
	 */
	public void close() throws IOException {
		reader.close();
	}
}
