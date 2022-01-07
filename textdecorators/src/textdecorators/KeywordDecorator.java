package textdecorators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;
import textdecorators.util.MyLogger.DebugLevel;

/**
 * @author Madhan Thangavel
 *
 */

/**
 * This decorator checks whether any given word in the keyword.txt is a keyword.
 *
 */
public class KeywordDecorator extends AbstractTextDecorator {

	private AbstractTextDecorator atd;
	private InputDetails id;

	public KeywordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		this.atd = atdIn;
		this.id = idIn;
	}

	/**
	 * Abstarct class implementation.
	 */
	@Override
	public void processInputDetails() {

		ArrayList<String> keyList = modifyListForMostFreq(id.getKeywords());
		List<String> aList = null;
		StringBuilder buffer = new StringBuilder();
		MyLogger.writeMessage("KEYWORD_DECORATOR - entered - KEYWORD_DECORATOR.", DebugLevel.KEYWORD_DECORATOR);
		MyLogger.writeMessage("Processing Input Details - KeywordDecorator - started .", DebugLevel.DEBUG);
		MyLogger.writeMessage("Processing Input Details - KeywordDecorator - started .", DebugLevel.KEYWORD_DECORATOR);

		for (int i = 0; i < keyList.size(); i++) {
			aList = Arrays.asList(id.getOutputBuffer().toString().trim().split(" "));
			for (int j = 0; j < aList.size(); j++) {
				String str = aList.get(j);
				if (str.replaceAll("\\.", "").replaceAll(",", "").equalsIgnoreCase(keyList.get(i).trim())) {
					if (str.contains("."))
						aList.set(j, "KEYWORD_" + str.replaceAll("\\.", "") + "_KEYWORD.");
					else if (str.contains(","))
						aList.set(j, "KEYWORD_" + str.replaceAll(",", "") + "_KEYWORD,");
					else
						aList.set(j, "KEYWORD_" + str + "_KEYWORD");
				}
				buffer.append(aList.get(j) + " ");
			}
			id.setOutputBuffer(buffer);
			buffer = new StringBuilder();
		}

		if (null != atd) {
			atd.processInputDetails();
		}

		MyLogger.writeMessage("Processing Input Details - KeywordDecorator - completed .", DebugLevel.DEBUG);
		MyLogger.writeMessage("Processing Input Details - KeywordDecorator - completed .",
				DebugLevel.KEYWORD_DECORATOR);
		MyLogger.writeMessage("KEYWORD_DECORATOR - exited - KEYWORD_DECORATOR.", DebugLevel.KEYWORD_DECORATOR);

	}

	/**
	 * To modify array list for MostFrequentWordDecorator.
	 * 
	 * @param list
	 * @return ArrayList<String>
	 */
	private ArrayList<String> modifyListForMostFreq(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			if (id.getMostFreqWord().equalsIgnoreCase(list.get(i))) {
				list.set(i, "MOST_FREQUENT_" + list.get(i) + "_MOST_FREQUENT");
			}
		}
		return list;
	}

}
