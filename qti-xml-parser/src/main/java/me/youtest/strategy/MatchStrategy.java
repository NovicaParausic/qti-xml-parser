package me.youtest.strategy;

import java.util.Collections;
import java.util.List;

import me.youtest.QtiUtil;
import me.youtest.model.Answer;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.MatchInteraction;

public class MatchStrategy implements InteractionParsingStrategy<MatchInteraction> {

	
	@Override
	public String parseQuestionText(MatchInteraction interaction) {
		return QtiUtil.parsePropmt(interaction.getPrompt());
	}
	
	@Override
	public List<Answer> extractAnswers(MatchInteraction matchInteraction) {
	
		List<Answer> answers = Collections.emptyList();
		/*
		List<SimpleMatchSet> simpleMatchSets = matchInteraction.getSimpleMatchSets();
		
		//ToDo find proper way for extracting answers 
		for (SimpleMatchSet simp : simpleMatchSets) {
			List<SimpleAssociableChoice> simpleAssociableChoices = simp.getSimpleAssociableChoices();
		}
		*/
		return answers;
	}
}
