package me.youtest.strategy;

import java.util.Collections;
import java.util.List;

import me.youtest.model.Answer;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.TextEntryInteraction;

public class TextEntryStrategy implements InteractionParsingStrategy<TextEntryInteraction> {

	
	@Override
	public List<Answer> extractAnswers(TextEntryInteraction interaction) {
		return Collections.emptyList();
	}

	@Override
	public String parseQuestionText(TextEntryInteraction interaction) {
		return "";
	}
}
