package me.youtest.strategy;

import java.util.List;

import me.youtest.model.Answer;
import me.youtest.model.Question;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.Interaction;

// Generic Adis
public interface InteractionParsingStrategy<T extends Interaction> {


	default Question parse(T interaction) {

		final String interactionId = interaction.getResponseIdentifier().toString();
		final String interactionType = interaction.getQtiClassName();

		Question question = new Question();
		question.setId(interactionId);
		question.setType(interactionType);

		final String text = parseQuestionText(interaction);
		question.setText(text);

		List<Answer> answers = extractAnswers(interaction);
		question.addAnswers(answers);

		return question;
	}

	List<Answer> extractAnswers(T interaction);

	String parseQuestionText(T interaction);
}
