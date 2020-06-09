package me.youtest;

import java.util.ArrayList;
import java.util.List;

import me.youtest.model.Question;
import me.youtest.strategy.InteractionParsingStrategy;
import uk.ac.ed.ph.jqtiplus.node.content.ItemBody;
import uk.ac.ed.ph.jqtiplus.node.item.AssessmentItem;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.Interaction;

public class QtiApp {

	public static void main(String[] args) {

		AssessmentItem assessmentItem = QtiUtil.extractAssessmentItem("multi_input.xml");
		ItemBody itemBody = assessmentItem.getItemBody();
		List<Interaction> interactions = itemBody.findInteractions();
		List<Question> questions = new ArrayList<>();

		for (Interaction interaction : interactions) {
			
			System.out.println(interaction.getQtiClassName());
			
			InteractionParsingStrategyFactory factory = new InteractionParsingStrategyFactory();
			InteractionParsingStrategy strategy = factory.produce(interaction.getQtiClassName());
			Question question = strategy.parse(interaction);
			
			questions.add(question);			
		}
	}
}
