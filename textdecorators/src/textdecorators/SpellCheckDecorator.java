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
 * This decorator checks whether any of the words is a misspelled word. A word
 * is misspelled if it is present in the file provided with the commandline
 * option -Dmisspelled. Misspelled words are prefixed with SPELLCHECK_ and
 * suffixed with _SPELLCHECK.
 * 
 */
public class SpellCheckDecorator extends AbstractTextDecorator {

	private AbstractTextDecorator atd;
	private InputDetails id;

	public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		atd = atdIn;
		id = idIn;
	}

	/**
	 * Implementation of Abstract base class.
	 */
	@Override
	public void processInputDetails() {

		ArrayList<String> spellCheckList = modifyListForMFAndKD(id.getMissSpellWords());
		List<String> aList = null;
		StringBuilder buffer = new StringBuilder();
		MyLogger.writeMessage("SPELL_CHECK_DECORATOR - entered - SPELL_CHECK_DECORATOR  .",
				DebugLevel.SPELLCHECK_DECORATOR);
		MyLogger.writeMessage("Processing Input Details - SpellCheckDecorator - started .", DebugLevel.DEBUG);
		MyLogger.writeMessage("Processing Input Details - SpellCheckDecorator - started .",
				DebugLevel.SPELLCHECK_DECORATOR);

		for (int i = 0; i < spellCheckList.size(); i++) {
			aList = Arrays.asList(id.getOutputBuffer().toString().trim().split(" "));
			for (int j = 0; j < aList.size(); j++) {
				String str = aList.get(j);
				if (str.replaceAll("\\.", "").replaceAll(",", "").equalsIgnoreCase(spellCheckList.get(i).trim())) {
					if (str.contains("."))
						aList.set(j, "SPELLCHECK_" + str.replaceAll("\\.", "") + "_SPELLCHECK.");
					else if (str.contains(","))
						aList.set(j, "SPELLCHECK_" + str.replaceAll(",", "") + "_SPELLCHECK,");
					else
						aList.set(j, "SPELLCHECK_" + str + "_SPELLCHECK");
				}
				buffer.append(aList.get(j) + " ");
			}
			id.setOutputBuffer(buffer);
			buffer = new StringBuilder();
		}

		if (null != atd) {
			atd.processInputDetails();
		}
		MyLogger.writeMessage("Processing Input Details - SpellCheckDecorator - completed .", DebugLevel.DEBUG);
		MyLogger.writeMessage("Processing Input Details - SpellCheckDecorator - completed .",
				DebugLevel.SPELLCHECK_DECORATOR);
		MyLogger.writeMessage("SPELL_CHECK_DECORATOR - exited - SPELL_CHECK_DECORATOR  .",
				DebugLevel.SPELLCHECK_DECORATOR);

	}

	/**
	 * To modify list for MostFrequentWord and Keyword decorator.
	 * 
	 * @param list
	 * @return ArrayList<String>
	 */
	private ArrayList<String> modifyListForMFAndKD(ArrayList<String> list) {

		for (int i = 0; i < list.size(); i++) {
			if (id.getMostFreqWord().equalsIgnoreCase(list.get(i))) {
				list.set(i, "MOST_FREQUENT_" + list.get(i) + "_MOST_FREQUENT");
			} else {
				for (int j = 0; j < id.getKeywords().size(); j++) {
					if (id.getKeywords().get(j).equalsIgnoreCase(list.get(i))) {
						list.set(i, "KEYWORD_" + list.get(i) + "_KEYWORD");
					}
				}
			}
		}
		return list;

	}

}
