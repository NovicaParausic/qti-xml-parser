package me.youtest;

import me.youtest.strategy.AssociateStrategy;
import me.youtest.strategy.ChoiceStrategy;
import me.youtest.strategy.GapMatchStrategy;
import me.youtest.strategy.InlineStrategy;
import me.youtest.strategy.InteractionParsingStrategy;
import me.youtest.strategy.MatchStrategy;
import me.youtest.strategy.OrderStrategy;
import me.youtest.strategy.TextEntryStrategy;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.Interaction;


public class InteractionParsingStrategyFactory {

	
	public InteractionParsingStrategy<? extends Interaction> produce(String qtiClassName) {

		switch (qtiClassName) {
		
			case "associateInteraction":
				return new AssociateStrategy();
			case "choiceInteraction":
				return new ChoiceStrategy();
			case "gapMatchInteraction":
				return new GapMatchStrategy();
			case "inlineChoiceInteraction":
				return new InlineStrategy();
			case "matchInteraction":
				return new MatchStrategy();
			case "orderInteraction":
				return new OrderStrategy();
			case "textEntryInteraction":
				return new TextEntryStrategy();
	
			default:
				throw new IllegalArgumentException();

		}
	}
}