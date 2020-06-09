package me.youtest.strategy;

import java.util.ArrayList;
import java.util.List;

import me.youtest.QtiUtil;
import me.youtest.model.Answer;
import uk.ac.ed.ph.jqtiplus.node.content.basic.FlowStatic;
import uk.ac.ed.ph.jqtiplus.node.content.basic.TextRun;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.ChoiceInteraction;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.Prompt;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.choice.SimpleChoice;

public class ChoiceStrategy implements InteractionParsingStrategy<ChoiceInteraction> {

	
	@Override
	public String parseQuestionText(ChoiceInteraction choiceInteraction) {
		Prompt prompt = choiceInteraction.getPrompt();
		String questionText = QtiUtil.parsePropmt(prompt);
		return questionText;
	}

	@Override
	public List<Answer> extractAnswers(ChoiceInteraction choiceInteraction) {

		List<SimpleChoice> simpleChoices = choiceInteraction.getSimpleChoices();
		List<Answer> answers = new ArrayList<>();
		for (SimpleChoice simpleChoice : simpleChoices) {
			Answer answer = parseAnswer(simpleChoice);
			answers.add(answer);
			// System.out.println(answer.toString());
		}

		return answers;
	}

	private Answer parseAnswer(SimpleChoice simple) {
		List<FlowStatic> inlineStatics = simple.getFlowStatics();
		FlowStatic flowStatic = inlineStatics.get(0);

		Answer answer = new Answer();

		if (flowStatic instanceof TextRun) {
			TextRun textRun = (TextRun) flowStatic;
			String text = textRun.getTextContent();
			answer.setText(text);
		}

		String identifier = simple.getIdentifier().toString();
		answer.setId(identifier);

		return answer;
	}

	/*
	 * public static Map<String, List<String>> findCorrectAnswers(AssessmentItem
	 * assessmentItem) {
	 * 
	 * List<ResponseDeclaration> responseDeclarations =
	 * assessmentItem.getResponseDeclarations(); Map<String, List<String>> ret = new
	 * HashMap<>();
	 * 
	 * for (ResponseDeclaration resp : responseDeclarations) { String respId =
	 * resp.getIdentifier().toString(); List<String> responses = new ArrayList<>();
	 * 
	 * CorrectResponse correctResponse = resp.getCorrectResponse(); List<FieldValue>
	 * fieldValues = correctResponse.getFieldValues(); for (FieldValue fieldValue :
	 * fieldValues) { String value = fieldValue.getSingleValue().toQtiString();
	 * responses.add(value); // System.out.println(value); } ret.put(respId,
	 * responses); } return ret;
	 * 
	 * }
	 */
}
