package textdecorators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;

import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;
import textdecorators.util.MyLogger.DebugLevel;

/**
 * @author Madhan Thangavel
 *
 */

/**
 * This decorator adds the prefix MOST_FREQUENT_ and suffix _MOST_FREQUENT to
 * all the occurrences of the most frequently occurring word in the entire input
 * file.
 *
 */
public class MostFrequentWordDecorator extends AbstractTextDecorator {

	private AbstractTextDecorator atd;
	private InputDetails id;

	public MostFrequentWordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		atd = atdIn;
		id = idIn;
	}

	/**
	 * Implementation of Abstract base class.
	 */
	@Override
	public void processInputDetails() {

		List<String> aList = splitAndTrimList(id.getInputData().trim());

		String freqWord = findFrequentWord(splitAndTrimList(id.getInputData().toLowerCase().trim()));

		MyLogger.writeMessage("MOSTFREQUENT_WORD_DECORATOR - entered - MOSTFREQUENT_WORD_DECORATOR .",
				DebugLevel.MOST_FREQ_WORD_DECORATOR);
		MyLogger.writeMessage("Processing Input Details - MostFrequentWordDecorator - started .", DebugLevel.DEBUG);
		MyLogger.writeMessage("Processing Input Details - MostFrequentWordDecorator - started .",
				DebugLevel.MOST_FREQ_WORD_DECORATOR);

		for (int i = 0; i < aList.size(); i++) {

			if (aList.get(i).equalsIgnoreCase(freqWord)) {
				String str = aList.get(i);
				aList.set(i, "MOST_FREQUENT_" + str + "_MOST_FREQUENT");
			}

			id.getOutputBuffer().append(aList.get(i) + " ");

		}
		id.setMostFreqWord(freqWord);
		if (null != atd) {
			atd.processInputDetails();
		}
		MyLogger.writeMessage("Processing Input Details - MostFrequentWordDecorator - completed .", DebugLevel.DEBUG);
		MyLogger.writeMessage("Processing Input Details - MostFrequentWordDecorator - completed .",
				DebugLevel.MOST_FREQ_WORD_DECORATOR);
		MyLogger.writeMessage("MOSTFREQUENT_WORD_DECORATOR - exited - MOSTFREQUENT_WORD_DECORATOR .",
				DebugLevel.MOST_FREQ_WORD_DECORATOR);
	}

	/**
	 * To split and remove the white spaces from list.
	 * 
	 * @param line
	 * @return List<String>
	 */
	private List<String> splitAndTrimList(String line) {

		List<String> list = Arrays.asList(line.split(" "));
		for (int i = 0; i < list.size(); i++) {
			list.set(i, list.get(i).trim());
		}
		return list;
	}

	/**
	 * To find the MostFrequentWord from the array list.
	 * 
	 * @param line
	 * @return String
	 */
	private String findFrequentWord(List<String> list) {

		LinkedHashSet<String> set = new LinkedHashSet<>(list);
		ArrayList<String> listNoDups = new ArrayList<>(set);
		HashMap<String, Integer> map = new HashMap<>();

		for (int i = 0; i < listNoDups.size(); i++) {
			map.put(listNoDups.get(i), Collections.frequency(list, listNoDups.get(i)));
		}

		int maxFreq = Collections.max(map.values());
		for (Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() == maxFreq) {
				return entry.getKey();
			}
		}
		return null;
	}

}
