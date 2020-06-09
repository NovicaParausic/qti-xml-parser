package me.youtest.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import me.youtest.model.Answer;
import uk.ac.ed.ph.jqtiplus.node.QtiNode;
import uk.ac.ed.ph.jqtiplus.node.content.basic.TextRun;
import uk.ac.ed.ph.jqtiplus.node.content.variable.TextOrVariable;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.InlineInteraction;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.choice.InlineChoice;

public class InlineStrategy implements InteractionParsingStrategy<InlineInteraction>{

	
	@Override
	public String parseQuestionText(InlineInteraction interaction) {
		return "";
	}
	
	@Override
	public List<Answer> extractAnswers(InlineInteraction inlineInteraction) {
		
		List<Answer> answers = new ArrayList<>();
		
		inlineInteraction.forEach(new Consumer<QtiNode>() {

			@Override
			public void accept(QtiNode node) {
				
				InlineChoice inlineChoice = (InlineChoice) node;
				Answer answer = parseAnswer(inlineChoice);
				answers.add(answer);
			}
			
		});
		
		return answers;
	}
	
	private Answer parseAnswer(InlineChoice inlineChoice) {
		
		Answer answer = new Answer();
		
		String answerId = inlineChoice.getIdentifier().toString();
		answer.setId(answerId);
		
		List<TextOrVariable> textList = inlineChoice.getTextOrVariables();
		
		TextOrVariable textOrVariable = textList.get(0);
		if (textOrVariable instanceof TextRun) {
			TextRun textRun = (TextRun) textOrVariable;
			String text = textRun.getTextContent();
			answer.setText(text);
		}
		
		return answer;
	}

	/*
	public void parseItemBody(ItemBody itemBody) {
		Iterator it = itemBody.iterator();
		List<Block> blocks = itemBody.getBlocks();
		
		//System.out.println("Block size " + blocks.size());
		for (Block block : blocks) {
			
			if (block instanceof Blockquote) {
				Blockquote blockquote = (Blockquote)block;
				List<Block> blocks1 = blockquote.getBlocks();
				for (Block block1 : blocks1) {
					P p = (P)block1;
					List<Inline> inlines = p.getInlines();
					
					
				}
				System.out.println();
			} else {
				P p = (P)block;
				List<Inline> inlines = p.getInlines();
				
				for(Inline inline : inlines) {
					String text = QtiUtil.parseTextRun(inline);
					System.out.println(text);
				
				}
				
			}
		}
		
		System.out.println();
	}*/
}
