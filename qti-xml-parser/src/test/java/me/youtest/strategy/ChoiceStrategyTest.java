package me.youtest.strategy;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import me.youtest.QtiUtil;
import me.youtest.model.Answer;
import me.youtest.model.Question;
import uk.ac.ed.ph.jqtiplus.node.content.ItemBody;
import uk.ac.ed.ph.jqtiplus.node.item.AssessmentItem;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.ChoiceInteraction;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.Interaction;

class ChoiceStrategyTest {

	private static AssessmentItem assessmentItem;

	private static ItemBody itemBody;

	private static ChoiceInteraction choiceInteraction;
	
	private static List<Interaction> interactions;
	
	
	@BeforeAll
	public static void initAssessmentItem() {

		assessmentItem = QtiUtil.extractAssessmentItem("choice.xml");
		
		itemBody = assessmentItem.getItemBody();
		
		interactions = itemBody.findInteractions();
		Interaction interaction = interactions.get(0);
		
		choiceInteraction = (ChoiceInteraction) interaction;
		
		
		//List<InlineStatic> inlineStatics = prompt.getInlineStatics();
	}
	
	
	@Test
	void interactionsSizeTest() {
		
		assertEquals(1, interactions.size());		
	}
	
	@Test
	void testParse() {
		
		ChoiceStrategy strategy = new ChoiceStrategy();
		Question question = strategy.parse(choiceInteraction);
		
		assertEquals("RESPONSE", question.getId());
		
		assertEquals("choiceInteraction", question.getType());
		
		assertEquals(3, question.getAnswers().size());
		
		List<Answer> answers = question.getAnswers();
		Answer answer0 = answers.get(0);
				
		assertAll("answer0",
					() -> assertEquals("ChoiceA", answer0.getId()), //TODO:
					() -> assertEquals("You must stay with your luggage at all times.", answer0.getText()),
					() -> assertEquals(false, answer0.isCorrect())//!!!!!
				);
		
		Answer answer1 = answers.get(1);
		
		assertAll("answer1",
				() -> assertEquals("ChoiceB", answer1.getId()), //TODO:
				() -> assertEquals("Do not let someone else look after your luggage.", answer1.getText()),
				() -> assertEquals(false, answer1.isCorrect())//!!!!!
			);
		
		Answer answer2 = answers.get(2);
		
		assertAll("answer2",
				() -> assertEquals("ChoiceC", answer2.getId()), //TODO:
				() -> assertEquals("Remember your luggage when you leave.", answer2.getText()),
				() -> assertEquals(false, answer2.isCorrect())//!!!!!
			);
	}
}
