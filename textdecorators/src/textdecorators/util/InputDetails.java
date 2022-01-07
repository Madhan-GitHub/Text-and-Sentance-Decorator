package textdecorators.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;

import textdecorators.exception.TextDecoratorException;
import textdecorators.util.MyLogger.DebugLevel;

/**
 * @author Madhan Thangavel
 *
 */

/**
 * Class to handle the input data from Input.txt, MisspelledWord.txt and
 * Keyword.txt.
 *
 */
public class InputDetails implements InputInterface {

	private String[] args;
	private StringBuilder inputData = new StringBuilder("");
	private ArrayList<String> keywords = new ArrayList<>();;
	private ArrayList<String> missSpellWords = new ArrayList<>();
	private StringBuilder outputBuffer = new StringBuilder("");
	private FileProcessor fp;

	private String mostFreqWord;

	/**
	 * @return String
	 */
	public String getMostFreqWord() {
		return mostFreqWord;
	}

	/**
	 * @param mostFreqWord
	 */
	public void setMostFreqWord(String mostFreqWord) {
		this.mostFreqWord = mostFreqWord;
	}

	/**
	 * @param args
	 * @exception TextDecoratorException On error encountered when handling the
	 *                                   input.
	 */
	public InputDetails(String[] args) throws TextDecoratorException {
		this.args = args;
		if (checkInputExist()) {
			fp = new FileProcessor();
			populateData();
		}
	}

	/**
	 * @return StringBuilder
	 */
	public StringBuilder getOutputBuffer() {
		return outputBuffer;
	}

	/**
	 * @param outputBuffer
	 */
	public void setOutputBuffer(StringBuilder outputBuffer) {
		this.outputBuffer = outputBuffer;
	}

	/**
	 * @return String
	 */
	public String getInputData() {
		return inputData.toString();
	}

	/**
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getKeywords() {
		return keywords;
	}

	/**
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getMissSpellWords() {
		return missSpellWords;
	}

	/**
	 * To check the given file exists.
	 * 
	 * @return boolean
	 * @throws TextDecoratorException on handling input data.
	 */
	private boolean checkInputExist() throws TextDecoratorException {
		for (int i = 0; i < this.args.length - 2; i++)
			if (!Files.exists(Paths.get(this.args[i])))
				throw new TextDecoratorException("Given file is not found - " + this.args[i]);

		return true;
	}

	/**
	 * To populate data set from input files.
	 * 
	 * @throws TextDecoratorException on handling input data.
	 */
	private void populateData() throws TextDecoratorException {

		try {

			String line = "";
			fp.initiateInput(this.args[0]);
			while ((line = fp.poll()) != null)
				if (line.length() == 0 || line.matches("[\\s]+"))
					continue;
				else if (line.matches("[a-zA-Z0-9\\.,\\s]+"))
					inputData.append(line);
				else
					throw new TextDecoratorException("Input file contains invalid characters");
			fp.close();

			fp.initiateInput(this.args[1]);
			while ((line = fp.poll()) != null) {
				line = line.trim();
				if (line.length() == 0 || line.matches("[\\s]+"))
					continue;
				else if (line.matches("[a-zA-Z0-9\\s]+"))
					missSpellWords.add(line);
				else
					throw new TextDecoratorException("Missspelled Words file contains invalid characters");
			}
			fp.close();

			fp.initiateInput(this.args[2]);
			while ((line = fp.poll()) != null) {
				line = line.trim();
				if (line.length() == 0 || line.matches("[\\s]+"))
					continue;
				else if (line.matches("[a-zA-Z0-9\\s]+"))
					keywords.add(line);
				else
					throw new TextDecoratorException("Keywords Words file contains invalid characters");
			}
			fp.close();

		} catch (FileNotFoundException e) {
			throw new TextDecoratorException("Given file is not found");
		} catch (InvalidPathException e) {
			throw new TextDecoratorException("Given input path is invalid");
		} catch (IOException e) {
			throw new TextDecoratorException("Error reading input file. Please check data");
		}

	}

	/**
	 * To write the result in Output.txt.
	 * 
	 * @throws TextDecoratorException on handling output data and file.
	 */
	@Override
	public void writeToFile() throws TextDecoratorException {
		File file = new File(this.args[3]);
		MyLogger.writeMessage("Output file - opened .", DebugLevel.DEBUG);
		PrintWriter pw = null;
		MyLogger.writeMessage("Output file - writing - started .", DebugLevel.DEBUG);

		try {
			file.createNewFile();
			pw = new PrintWriter(file);
			pw.write(outputBuffer.toString());
			MyLogger.writeMessage("Output file - writing - completed .", DebugLevel.DEBUG);
		} catch (FileNotFoundException e) {
			throw new TextDecoratorException("Error creating output file");
		} catch (IOException e) {
			throw new TextDecoratorException("Error writing to output file");
		} catch (SecurityException e) {
			throw new TextDecoratorException("Write access is denied");
		} finally {
			if (pw != null)
				pw.close();
			MyLogger.writeMessage("Output file - closed .", DebugLevel.DEBUG);
		}
	}

}
