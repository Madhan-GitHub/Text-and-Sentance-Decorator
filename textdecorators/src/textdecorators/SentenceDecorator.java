package textdecorators;

import textdecorators.util.InputDetails;
import textdecorators.util.MyLogger;
import textdecorators.util.MyLogger.DebugLevel;

/**
 * @author Madhan Thangavel
 *
 */

/**
 * The SentenceDecorator prefixes the sentence with BEGIN_SENTENCE__ and
 * suffixes the sentence with __END_SENTENCE.
 * 
 */
public class SentenceDecorator extends AbstractTextDecorator {

	private AbstractTextDecorator atd;
	private InputDetails id;

	public SentenceDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		atd = atdIn;
		id = idIn;
	}

	/**
	 * Implementation of Abstract base class.
	 */
	@Override
	public void processInputDetails() {

		StringBuilder buffer = new StringBuilder("");
		String str = null;
		buffer.append("BEGIN_SENTENCE__");
		MyLogger.writeMessage("SENTENCE_DECORATOR - entered - SENTENCE_DECORATOR .", DebugLevel.SENTENCE_DECORATOR);
		MyLogger.writeMessage("Processing Input Details - SentenceDecorator - started .", DebugLevel.DEBUG);
		MyLogger.writeMessage("Processing Input Details - SentenceDecorator - started .",
				DebugLevel.SENTENCE_DECORATOR);

		if (id.getOutputBuffer().toString().trim().charAt(id.getOutputBuffer().toString().trim().length() - 1) == '.')
			str = id.getOutputBuffer().toString().trim().substring(0,
					id.getOutputBuffer().toString().trim().length() - 1);
		else
			str = id.getOutputBuffer().toString().trim();

		buffer.append(str.replaceAll("\\.", "__END_SENTENCE.BEGIN_SENTENCE__"));

		buffer.append("__END_SENTENCE.");
		id.setOutputBuffer(buffer);

		if (null != atd) {
			atd.processInputDetails();
		}
		MyLogger.writeMessage("Processing Input Details - SentenceDecorator - completed .", DebugLevel.DEBUG);
		MyLogger.writeMessage("Processing Input Details - SentenceDecorator - completed .",
				DebugLevel.SENTENCE_DECORATOR);
		MyLogger.writeMessage("SENTENCE_DECORATOR - exited - SENTENCE_DECORATOR .", DebugLevel.SENTENCE_DECORATOR);

	}

}
