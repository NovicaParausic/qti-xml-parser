package me.youtest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.youtest.model.Answer;
import me.youtest.model.Question;
import me.youtest.strategy.InteractionParsingStrategy;
import uk.ac.ed.ph.jqtiplus.node.content.ItemBody;
import uk.ac.ed.ph.jqtiplus.node.item.AssessmentItem;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.Interaction;

class QtiAppTest {

	private AssessmentItem assessmentItem;

	private ItemBody itemBody;

	private List<Interaction> interactions;

	private InteractionParsingStrategyFactory factory;
	
	@BeforeEach
	public void initAssessmentItem() {

		assessmentItem = QtiUtil.extractAssessmentItem("multi_input.xml");

		itemBody = assessmentItem.getItemBody();

		interactions = itemBody.findInteractions();

		factory = new InteractionParsingStrategyFactory();
		
		for (Interaction inter : interactions) {
			System.out.println(inter.getQtiClassName());
		}
	}

	@Test
	void interactionsSizeTest() {

		assertEquals(4, interactions.size());
	}

	@Test
	@DisplayName("Choice Interaction ")
	void testChoiceInteraction() {

		Interaction interaction = interactions.get(0);
		InteractionParsingStrategy strategy = factory.produce(interaction.getQtiClassName());
		
		Question question = strategy.parse(interaction);

		assertEquals("RESPONSE1", question.getId());

		assertEquals("choiceInteraction", question.getType());

		assertEquals(3, question.getAnswers().size());

		List<Answer> answers = question.getAnswers();
		
		Answer answer0 = answers.get(0);
		assertAll("answer0", 
				() -> assertEquals("ChoiceA", answer0.getId()), // TODO:
				() -> assertEquals("Some people are afraid of a woman who walks around at night as a ghost.", answer0.getText()), 
				() -> assertEquals(false, answer0.isCorrect())// !!!!!
		);
		
		Answer answer1 = answers.get(1);
		assertAll("answer1", 
				() -> assertEquals("ChoiceB", answer1.getId()), // TODO:
				() -> assertEquals("Some people are afraid of the dark.", answer1.getText()), 
				() -> assertEquals(false, answer1.isCorrect())// !!!!!
		);
		
		Answer answer2 = answers.get(2);
		assertAll("answer2", 
				() -> assertEquals("ChoiceC", answer2.getId()), // TODO:
				() -> assertEquals("Some people are afraid of a man who walks around at night as a ghost.", answer2.getText()), 
				() -> assertEquals(false, answer2.isCorrect())// !!!!!
		);

	}
	
	@Test
	@DisplayName("Inline Choice Interaction ")
	void testInlineChoiceInteraction() {

		Interaction interaction = interactions.get(1);
		InteractionParsingStrategy strategy = factory.produce(interaction.getQtiClassName());
		
		Question question = strategy.parse(interaction);

		assertEquals("RESPONSE2", question.getId());

		assertEquals("inlineChoiceInteraction", question.getType());

		assertEquals(4, question.getAnswers().size());
	}
	
	@Test
	@DisplayName("Text Entry Interaction")
	void testTextEntryInteraction() {

		Interaction interaction = interactions.get(2);
		InteractionParsingStrategy strategy = factory.produce(interaction.getQtiClassName());
		
		Question question = strategy.parse(interaction);

		assertEquals("RESPONSE3", question.getId());

		assertEquals("textEntryInteraction", question.getType());	
	}
	
	@Test
	@DisplayName("Gap Match Interaction")
	void testGapMatchInteraction() {

		Interaction interaction = interactions.get(3);
		InteractionParsingStrategy strategy = factory.produce(interaction.getQtiClassName());
		
		Question question = strategy.parse(interaction);

		assertEquals("RESPONSE4", question.getId());

		assertEquals("gapMatchInteraction", question.getType());	
	}

}
