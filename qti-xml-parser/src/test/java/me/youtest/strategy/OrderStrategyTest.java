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
import uk.ac.ed.ph.jqtiplus.node.item.interaction.Interaction;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.OrderInteraction;

class OrderStrategyTest {

	private static AssessmentItem assessmentItem;

	private static ItemBody itemBody;

	private static OrderInteraction orderInteraction;

	private static List<Interaction> interactions;

	@BeforeAll
	public static void initAssessmentItem() {

		assessmentItem = QtiUtil.extractAssessmentItem("order.xml");

		itemBody = assessmentItem.getItemBody();

		interactions = itemBody.findInteractions();
		Interaction interaction = interactions.get(0);

		orderInteraction = (OrderInteraction) interaction;

	}

	@Test
	void interactionsSizeTest() {

		assertEquals(1, interactions.size());
	}

	@Test
	void testParse() {

		OrderStrategy strategy = new OrderStrategy();
		Question question = strategy.parse(orderInteraction);

		assertEquals("RESPONSE", question.getId());

		assertEquals("orderInteraction", question.getType());

		assertEquals(3, question.getAnswers().size());

		List<Answer> answers = question.getAnswers();
		Answer answer0 = answers.get(0);

		assertAll("answer0", 
				() -> assertEquals("DriverA", answer0.getId()), // TODO:
				() -> assertEquals("Rubens Barrichello", answer0.getText()), 
				() -> assertEquals(false, answer0.isCorrect())// !!!!!
		);

		Answer answer1 = answers.get(1);

		assertAll("answer1", 
				() -> assertEquals("DriverB", answer1.getId()), // TODO:
				() -> assertEquals("Jenson Button", answer1.getText()), 
				() -> assertEquals(false, answer1.isCorrect())// !!!!!
		);

		Answer answer2 = answers.get(2);

		assertAll("answer2", 
				() -> assertEquals("DriverC", answer2.getId()), // TODO:
				() -> assertEquals("Michael Schumacher", answer2.getText()), 
				() -> assertEquals(false, answer2.isCorrect())// !!!!!
		);	
	}

}
