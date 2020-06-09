package me.youtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.youtest.strategy.InteractionParsingStrategy;

class InteractionParsingStrategyFactoryTest {

	private static InteractionParsingStrategyFactory factory;
	
	private static InteractionParsingStrategy strategy;
	
	@BeforeAll
	public static void init() {
		
		factory = new InteractionParsingStrategyFactory();
	}
	
	
	@Test
	@DisplayName("Associate Strategy")
	public void associateTest() {
		
		strategy = factory.produce("associateInteraction");
		Assertions.assertEquals("AssociateStrategy", strategy.getClass().getSimpleName());
	}
	
	@Test
	@DisplayName("Choice Strategy")
	public void choiceTest() {
		
		strategy = factory.produce("choiceInteraction");
		Assertions.assertEquals("ChoiceStrategy", strategy.getClass().getSimpleName());
	}
	
	@Test
	@DisplayName("Inline Strategy")
	public void inlineTest() {
		
		strategy = factory.produce("inlineChoiceInteraction");
		Assertions.assertEquals("InlineStrategy", strategy.getClass().getSimpleName());
	}
	
	@Test
	@DisplayName("Match Strategy")
	public void matchTest() {
		
		strategy = factory.produce("matchInteraction");
		Assertions.assertEquals("MatchStrategy", strategy.getClass().getSimpleName());
	}
	
	@Test
	@DisplayName("Order Strategy")
	public void orderTest() {
		
		strategy = factory.produce("orderInteraction");
		Assertions.assertEquals("OrderStrategy", strategy.getClass().getSimpleName());
	}
}
