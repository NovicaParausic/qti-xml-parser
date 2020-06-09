package me.youtest.strategy;

import java.util.Collections;
import java.util.List;

import me.youtest.model.Answer;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.GapMatchInteraction;

public class GapMatchStrategy implements InteractionParsingStrategy<GapMatchInteraction> {


	@Override
	public List<Answer> extractAnswers(GapMatchInteraction interaction) {
		return Collections.emptyList();
	}

	@Override
	public String parseQuestionText(GapMatchInteraction interaction) {
		return "";
	}
}
