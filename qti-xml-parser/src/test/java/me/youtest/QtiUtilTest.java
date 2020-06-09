package me.youtest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import uk.ac.ed.ph.jqtiplus.node.content.ItemBody;
import uk.ac.ed.ph.jqtiplus.node.content.basic.InlineStatic;
import uk.ac.ed.ph.jqtiplus.node.item.AssessmentItem;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.ChoiceInteraction;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.Interaction;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.Prompt;

class QtiUtilTest {

	private AssessmentItem assessmentItem;

	private Prompt prompt;
	
	private InlineStatic inlineStatic;
	
	@BeforeEach
	public void initAssessmentItem() {

		assessmentItem = QtiUtil.extractAssessmentItem("choice.xml");
		
		ItemBody itemBody = assessmentItem.getItemBody();
		List<Interaction> interactions = itemBody.findInteractions();
		Interaction interaction = interactions.get(0);
		
		ChoiceInteraction choiceInteraction = (ChoiceInteraction) interaction;
		prompt = choiceInteraction.getPrompt();
		
		List<InlineStatic> inlineStatics = prompt.getInlineStatics();
		inlineStatic = inlineStatics.get(0);
	}
		
	@Test
	@DisplayName("Item Test")
	public void assesmentItemTest() {

		assertEquals("choice", assessmentItem.getIdentifier());
	}

	@Test
	@DisplayName("Parse Prompt")
	public void parsePromptTest() {

		String promptString = QtiUtil.parsePropmt(prompt);
		String[] promptSplit = promptString.split(" ");
		assertEquals("What", promptSplit[0]);
	}
	
	@Test
	@DisplayName("Text Run")
	public void parseTextRunTest() {

		String textRun = QtiUtil.parseTextRun(inlineStatic); 
		String[] textArray = textRun.split(" ");
		assertEquals("What", textArray[0]);
	}
}
