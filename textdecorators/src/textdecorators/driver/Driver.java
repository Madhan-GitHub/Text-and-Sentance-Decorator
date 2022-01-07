package textdecorators.driver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import textdecorators.AbstractTextDecorator;
import textdecorators.KeywordDecorator;
import textdecorators.MostFrequentWordDecorator;
import textdecorators.SentenceDecorator;
import textdecorators.SpellCheckDecorator;
import textdecorators.exception.TextDecoratorException;
import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;
import textdecorators.util.MyLogger.DebugLevel;

/**
 * @author Madhan Thangavel
 *
 */
public class Driver {

	/**
	 * Driver code to program with decorator pattern using datastructure(s) to
	 * store, retrieve and update sentences.
	 * 
	 * @param args
	 * @return String The processed line read from the input file.
	 * @exception TextDecoratorException On error encountered when handling the
	 *                                   input.
	 */
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as input, misspelled, keywords, and
		 * output in case the argument value is not given java takes the default value
		 * specified in build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 5) || (args[0].equals("${input}")) || (args[1].equals("${misspelled}"))
				|| (args[2].equals("${keywords}")) || (args[3].equals("${output}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts 4 arguments.");
			System.exit(0);
		}
		/**
		 * Input file validation.
		 */
		if (!Files.exists(Paths.get(args[0])) || !Files.exists(Paths.get(args[1]))
				|| !Files.exists(Paths.get(args[2]))) {
			System.err.printf("Error: Given Input.txt or MisspelledWord.txt or Keywords.txt  doesn't exist..");
			System.exit(0);
		}
		/**
		 * Same path or same Filename validation.
		 */
		if ((Paths.get(args[0]).getFileName().toString().equals(Paths.get(args[1]).getFileName().toString()))
				|| (Paths.get(args[0]).getFileName().toString().equals(Paths.get(args[2]).getFileName().toString()))
				|| (Paths.get(args[1]).getFileName().toString().equals(Paths.get(args[2]).getFileName().toString()))) {
			System.err.printf(
					"Error: Either of Input.txt or MisspelledWord.txt or Keywords.txt  have same path and file name.");
			System.exit(0);
		}

		InputDetails inputD = null;
		AbstractTextDecorator sentenceDecorator = null;
		AbstractTextDecorator spellCheckDecorator = null;
		AbstractTextDecorator keywordDecorator = null;
		AbstractTextDecorator mostFreqWordDecorator = null;

		try {
			if (new File(args[0]).length() > 0 && new File(args[1]).length() > 0 && new File(args[2]).length() > 0) {

				MyLogger.setDebugValue(Integer.parseInt(args[4]));
				MyLogger.writeMessage("Lets gets started with assignment - Text Decorator...!", DebugLevel.DEBUG);

				inputD = new InputDetails(args);
				MyLogger.writeMessage("Instantiated - Input Details.", DebugLevel.DEBUG);

				sentenceDecorator = new SentenceDecorator(null, inputD);
				MyLogger.writeMessage("Instantiated - Sentance Decorator.", DebugLevel.DEBUG);

				spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD);
				MyLogger.writeMessage("Instantiated - Spell Check Decorator.", DebugLevel.DEBUG);

				keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD);
				MyLogger.writeMessage("Instantiated - Keyword Decorator.", DebugLevel.DEBUG);

				mostFreqWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD);
				MyLogger.writeMessage("Instantiated - Most Frequent Word Decorator.", DebugLevel.DEBUG);

				MyLogger.writeMessage("Process - Input Details - started.", DebugLevel.DEBUG);
				mostFreqWordDecorator.processInputDetails();
				MyLogger.writeMessage("Process - Input Details - completed.", DebugLevel.DEBUG);

				inputD.writeToFile();

				MyLogger.writeMessage("Check the result in Output.txt.", DebugLevel.DEBUG);
			} else if (new File(args[1]).length() > 0 && new File(args[2]).length() > 0) {
				String msg = "Input.txt is either empty or blank. Terminating code....";
				throw new TextDecoratorException(msg);
			} else if (new File(args[0]).length() > 0 && new File(args[2]).length() > 0) {
				String msg = "MisspelledWords.txt is either empty or blank. Terminating code....";
				throw new TextDecoratorException(msg);
			} else if (new File(args[0]).length() > 0 && new File(args[1]).length() > 0) {
				String msg = "Keywords.txt is either empty or blank. Terminating code....";
				throw new TextDecoratorException(msg);
			} else {
				String msg = "Either Input.txt or MisspelledWords.txt or Keywords.txt  is empty or blank. Terminating code....";
				throw new TextDecoratorException(msg);
			}
		} catch (TextDecoratorException e) {
			System.err.println(e.getMessage());
		}

	}

}
