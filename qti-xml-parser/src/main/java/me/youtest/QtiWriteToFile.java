package me.youtest;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import me.youtest.model.Answer;
import me.youtest.model.Question;
import uk.ac.ed.ph.jqtiplus.SimpleJqtiFacade;
import uk.ac.ed.ph.jqtiplus.node.content.ItemBody;
import uk.ac.ed.ph.jqtiplus.node.content.basic.TextRun;
import uk.ac.ed.ph.jqtiplus.node.item.AssessmentItem;
import uk.ac.ed.ph.jqtiplus.node.item.CorrectResponse;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.ChoiceInteraction;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.Prompt;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.choice.SimpleChoice;
import uk.ac.ed.ph.jqtiplus.node.item.response.declaration.ResponseDeclaration;
import uk.ac.ed.ph.jqtiplus.node.item.response.processing.ResponseProcessing;
import uk.ac.ed.ph.jqtiplus.node.shared.FieldValue;
import uk.ac.ed.ph.jqtiplus.reading.QtiObjectReadResult;
import uk.ac.ed.ph.jqtiplus.reading.QtiXmlInterpretationException;
import uk.ac.ed.ph.jqtiplus.serialization.QtiSerializer;
import uk.ac.ed.ph.jqtiplus.types.Identifier;
import uk.ac.ed.ph.jqtiplus.value.BaseType;
import uk.ac.ed.ph.jqtiplus.value.Cardinality;
import uk.ac.ed.ph.jqtiplus.value.IdentifierValue;
import uk.ac.ed.ph.jqtiplus.xmlutils.XmlResourceNotFoundException;
import uk.ac.ed.ph.jqtiplus.xmlutils.locators.ClassPathResourceLocator;
import uk.ac.ed.ph.jqtiplus.xmlutils.locators.ResourceLocator;

public class QtiWriteToFile {

	 public static void main(final String[] args) throws Exception {
		 System.out.println("bla bla");
	     
		 Question ques1 = new Question("q1", "sta?");
		 List<Answer> answers1 = Arrays.asList(
				 	new Answer("a1", "da", false),
				 	new Answer("a2", "ne", true)
				 );
		 ques1.addAnswers(answers1);
		 
		 Question ques2 = new Question("q2", "ko?");
		 List<Answer> answers2 = Arrays.asList(
				 	new Answer("a3", "aha", false),
				 	new Answer("a4", "mhm", true)
				 );
		 ques2.addAnswers(answers2);
		 
		 //answers1.stream().forEach(s -> System.out.println(s.toString()));
		 List<Answer> correctAnsw = findCorrectAnswer(ques1);
		 correctAnsw.stream().forEach(s -> System.out.println(s.toString()));
		 System.out.println();
		 System.out.println(writeString(ques1));

	 }
	  
	 public static void writeToFile(String xmlSource, String fileName) 
			 throws SAXException, ParserConfigurationException, IOException, 
			 	TransformerException, TransformerConfigurationException {
		 
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		 DocumentBuilder builder = factory.newDocumentBuilder();
		 Document doc = builder.parse(new InputSource(new StringReader(xmlSource)));
		 
		 TransformerFactory transformerFactory = TransformerFactory.newInstance();
		 Transformer transformer = transformerFactory.newTransformer();
		 DOMSource source = new DOMSource(doc);
		 
		 StreamResult result = new StreamResult(new File(fileName + ".xml"));
		 transformer.transform(source, result);
		 
		 //https://stackoverflow.com/questions/17853541/java-how-to-convert-a-xml-string-into-an-xml-file
	 }
	 
	 public static List<Answer> findCorrectAnswer(Question question) {
		 return question.getAnswers().stream()
		 	.filter(answer -> answer.isCorrect() == true)
		 	.collect(Collectors.toList());
	 }	 
	 
	 public static String writeString(Question question) {
		 final ResourceLocator inputResourceLocator = new ClassPathResourceLocator();
	     final URI inputUri = URI.create("classpath:/minimal.xml");

	        //Load the QTI XML, perform schema validation, and build a JQTI+ Object model from it,
	        // expecting an AssessmentItem.
	         // (We're going to use SimpleJqtiFacade here)
	         
	     final SimpleJqtiFacade simpleJqtiFacade = new SimpleJqtiFacade();
	     QtiObjectReadResult<AssessmentItem> readResult = null;
	     try {
	           readResult = simpleJqtiFacade.readQtiRootNode(inputResourceLocator, inputUri,
	                    true, // = perform schema validation 
	                    AssessmentItem.class);
	     }
	     catch (final XmlResourceNotFoundException e) {
	            // This Exception will be thrown the example file could not be found
	             // using the ResourceLocator we set up above.
	            //throw e;
	    	 e.printStackTrace();
	     }
	     catch (final QtiXmlInterpretationException e) {
	            // This is thrown if a JQTI+ Object model could not be constructed from the QTI XML,
	             // or the resulting model wasn't an AssessmentItem.
	            //throw e;
	    	 e.printStackTrace();
	     }
		 
	     final AssessmentItem assessmentItem = readResult.getRootNode();
	        
	     final ResponseDeclaration responseDeclaration = new ResponseDeclaration(assessmentItem);
	     assessmentItem.getResponseDeclarations().add(responseDeclaration);
	     responseDeclaration.setIdentifier(Identifier.assumedLegal(question.getId()));
	     responseDeclaration.setCardinality(Cardinality.SINGLE);
	     responseDeclaration.setBaseType(BaseType.IDENTIFIER);
		 
	     List<Answer> correctAnswers = findCorrectAnswer(question);
	     for (Answer answ : correctAnswers) {    
	     	final CorrectResponse correctResponse = new CorrectResponse(responseDeclaration);
		     responseDeclaration.setCorrectResponse(correctResponse);
		     correctResponse.getFieldValues().add(new FieldValue(correctResponse, new IdentifierValue(answ.getText())));
		 }   
	        
	     final ItemBody itemBody = assessmentItem.getItemBody();
	     final ChoiceInteraction choiceInteraction = new ChoiceInteraction(itemBody);
	     itemBody.getBlocks().add(choiceInteraction);
	     choiceInteraction.setResponseIdentifier(responseDeclaration.getIdentifier());
	     choiceInteraction.setShuffle(true);
	     choiceInteraction.setMaxChoices(1);
	     final Prompt prompt = new Prompt(choiceInteraction);
	     choiceInteraction.setPrompt(prompt);
	     prompt.getInlineStatics().add(new TextRun(prompt, question.getText()));
	        
	     for (Answer answ : question.getAnswers()) {
		     final SimpleChoice simpleChoice1 = new SimpleChoice(choiceInteraction);
		     simpleChoice1.setIdentifier(Identifier.assumedLegal(answ.getId()));
		     simpleChoice1.getFlowStatics().add(new TextRun(simpleChoice1, answ.getText()));
		     choiceInteraction.getSimpleChoices().add(simpleChoice1);
	     }
	     /*
	     final SimpleChoice simpleChoice2 = new SimpleChoice(choiceInteraction);
	     simpleChoice2.setIdentifier(Identifier.assumedLegal("CHOICE2"));
	     simpleChoice2.getFlowStatics().add(new TextRun(simpleChoice1, "Choice 2"));
	     choiceInteraction.getSimpleChoices().add(simpleChoice2);
	     */   
	        //We'll add a responseProcessing as well, using one of the default templates
	     final ResponseProcessing responseProcessing = new ResponseProcessing(assessmentItem);
	     responseProcessing.setTemplate(URI.create("http://www.imsglobal.org/questin/qti_v2p1/rptemplates/match_correct"));
	     assessmentItem.setResponseProcessing(responseProcessing);
	        
	        //Finally we serialize the updated assessmentItem to an XML string and print it out
	     final QtiSerializer qtiSerializer = simpleJqtiFacade.createQtiSerializer();
	        
	     String textAssessment = qtiSerializer.serializeJqtiObject(assessmentItem);
	     return textAssessment;	        
	 }

}
