package textdecorators.util;

/**
 * @author Madhan Thangavel
 *
 */

/**
 * Logger class implemented based on the given schematics. (1.Debug
 * 2.MostfreqWord decorator 3. Keyword decorator 4. SpellCheck decorator
 * 5.Sentence decorator 6.Error).
 *
 */
public class MyLogger {

	public static enum DebugLevel {
		NONE, DEBUG, ERROR, KEYWORD_DECORATOR, MOST_FREQ_WORD_DECORATOR, SENTENCE_DECORATOR, SPELLCHECK_DECORATOR
	};

	private static DebugLevel debugLevel;

	/**
	 * @param levelIn
	 */
	public static void setDebugValue(int levelIn) {
		switch (levelIn) {
			case 6:
				debugLevel = DebugLevel.ERROR;
				break;
			case 5:
				debugLevel = DebugLevel.SENTENCE_DECORATOR;
				break;
			case 4:
				debugLevel = DebugLevel.SPELLCHECK_DECORATOR;
				break;
			case 3:
				debugLevel = DebugLevel.KEYWORD_DECORATOR;
				break;
			case 2:
				debugLevel = DebugLevel.MOST_FREQ_WORD_DECORATOR;
				break;
			case 1:
				debugLevel = DebugLevel.DEBUG;
				break;
			default:
				debugLevel = DebugLevel.NONE;
				break;
		}
	}

	/**
	 * @param levelIn
	 */
	public static void setDebugValue(DebugLevel levelIn) {
		debugLevel = levelIn;
	}

	/**
	 * @param message
	 * @param levelIn
	 */
	public static void writeMessage(String message, DebugLevel levelIn) {
		if (levelIn == debugLevel)
			System.out.println(message);
	}

	/**
	 * @return String
	 */
	public String toString() {
		return "The debug level has been set to the following " + debugLevel;
	}
}
