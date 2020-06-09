 package me.youtest.strategy;

import java.util.ArrayList;
import java.util.List;

import me.youtest.QtiUtil;
import me.youtest.model.Answer;
import uk.ac.ed.ph.jqtiplus.node.content.basic.FlowStatic;
import uk.ac.ed.ph.jqtiplus.node.content.basic.TextRun;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.AssociateInteraction;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.choice.SimpleAssociableChoice;

public class AssociateStrategy implements InteractionParsingStrategy<AssociateInteraction> {

	
	@Override
	public String parseQuestionText(AssociateInteraction interaction) {
		return QtiUtil.parsePropmt(interaction.getPrompt());
	}
	
	@Override
	public List<Answer> extractAnswers(AssociateInteraction associateInteraction) {
		
		List<SimpleAssociableChoice> simpleAssociableChoices = associateInteraction.getSimpleAssociableChoices();
		List<Answer> answers = new ArrayList<>();
		
		for (SimpleAssociableChoice simp : simpleAssociableChoices) {
			Answer answer = new Answer();
			answer = parseAnswer(simp); 
			answers.add(answer);
		}
		
		return answers;
	}
	
	private  Answer parseAnswer(SimpleAssociableChoice simple) {
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
