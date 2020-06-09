package me.youtest;

import java.net.URI;
import java.util.Iterator;
import java.util.List;

import uk.ac.ed.ph.jqtiplus.SimpleJqtiFacade;
import uk.ac.ed.ph.jqtiplus.attribute.Attribute;
import uk.ac.ed.ph.jqtiplus.attribute.AttributeList;
import uk.ac.ed.ph.jqtiplus.node.QtiNode;
import uk.ac.ed.ph.jqtiplus.node.content.basic.InlineStatic;
import uk.ac.ed.ph.jqtiplus.node.content.basic.TextRun;
import uk.ac.ed.ph.jqtiplus.node.item.AssessmentItem;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.Prompt;
import uk.ac.ed.ph.jqtiplus.reading.QtiObjectReadResult;
import uk.ac.ed.ph.jqtiplus.reading.QtiXmlInterpretationException;
import uk.ac.ed.ph.jqtiplus.serialization.QtiSerializer;
import uk.ac.ed.ph.jqtiplus.xmlutils.XmlResourceNotFoundException;
import uk.ac.ed.ph.jqtiplus.xmlutils.locators.ClassPathResourceLocator;
import uk.ac.ed.ph.jqtiplus.xmlutils.locators.ResourceLocator;

public class QtiUtil {

	public static AssessmentItem extractAssessmentItem(String fileName) {
		final ResourceLocator inputResourceLocator = new ClassPathResourceLocator();
		final URI inputUri = URI.create("classpath:/" + fileName);

		// Load the QTI XML, perform schema validation, and build a JQTI+ Object
		// model from it,
		// expecting an AssessmentItem.
		// (We're going to use SimpleJqtiFacade here)

		final SimpleJqtiFacade simpleJqtiFacade = new SimpleJqtiFacade();
		QtiObjectReadResult<AssessmentItem> readResult = null;
		try {
			readResult = simpleJqtiFacade.readQtiRootNode(inputResourceLocator, inputUri, true, // =
					// perform
					// schema
					// validation
					AssessmentItem.class);
		} catch (final XmlResourceNotFoundException e) {
			// This Exception will be thrown the example file could not be found
			// using the ResourceLocator we set up above.
			// throw e;
			e.printStackTrace();
		} catch (final QtiXmlInterpretationException e) {
			// This is thrown if a JQTI+ Object model could not be constructed
			// from the QTI XML,
			// or the resulting model wasn't an AssessmentItem.
			// throw e;
			e.printStackTrace();
		}

		final AssessmentItem assessmentItem = readResult.getRootNode();

		return assessmentItem;
	}

	public static String parsePropmt(Prompt prompt) {

		List<InlineStatic> inlineStatics = prompt.getInlineStatics();

		InlineStatic inlineStatic = inlineStatics.get(0);
		String text = null;
		if (inlineStatic instanceof TextRun) {
			TextRun textRun = (TextRun) inlineStatic;
			text = textRun.getTextContent();
			// System.out.println(text);
		}

		return text;
	}
	
	public static String parseTextRun(QtiNode qtiNode) {
		
		String text = null;
		if (qtiNode instanceof TextRun) {
			TextRun textRun = (TextRun) qtiNode;
			text = textRun.getTextContent().trim();
		}
		
		return text;
	}
	
	public static void iterateAttributeList(AttributeList attributeList) {
		Iterator<Attribute<?>> iterator = attributeList.iterator();
		System.out.println();
		while (iterator.hasNext()) {
			Attribute<?> attribute = iterator.next();
			System.out.println("Get Class " + attribute.getClass().getName());
			System.out.println("Get Local Name " + attribute.getLocalName());
			System.out.println("Get Value " + attribute.getValue());
			System.out.println();
		}
	}
	
	public static void print(QtiNode qtiNode) {
		// this method is used for printing different QtiNodes like
		// ChoiceInteraction, ItemBody, AssessmentItem etc
		final SimpleJqtiFacade simpleJqtiFacade = new SimpleJqtiFacade();
		final QtiSerializer qtiSerializer = simpleJqtiFacade.createQtiSerializer();
		String qtiNodeString = qtiSerializer.serializeJqtiObject(qtiNode);
		System.out.println(qtiNodeString);
	}
}
