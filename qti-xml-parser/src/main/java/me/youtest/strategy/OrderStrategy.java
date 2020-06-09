package me.youtest.strategy;

import java.util.ArrayList;
import java.util.List;

import me.youtest.QtiUtil;
import me.youtest.model.Answer;
import uk.ac.ed.ph.jqtiplus.node.content.basic.FlowStatic;
import uk.ac.ed.ph.jqtiplus.node.content.basic.TextRun;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.OrderInteraction;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.choice.SimpleChoice;

public class OrderStrategy implements InteractionParsingStrategy<OrderInteraction> {
	

	@Override
	public String parseQuestionText(OrderInteraction orderInteraction) {
		return QtiUtil.parsePropmt(orderInteraction.getPrompt());
	}

	@Override
	public List<Answer> extractAnswers(OrderInteraction orderInteraction) {

		List<SimpleChoice> simpleChoices = orderInteraction.getSimpleChoices();
		List<Answer> answers = new ArrayList<>();

		for (SimpleChoice simp : simpleChoices) {
			Answer answer = new Answer();
			answer = parseAnswer(simp);
			answers.add(answer);
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
}
