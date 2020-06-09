package me.youtest.strategy;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.youtest.QtiUtil;
import me.youtest.model.Answer;
import me.youtest.model.Question;
import uk.ac.ed.ph.jqtiplus.node.content.ItemBody;
import uk.ac.ed.ph.jqtiplus.node.item.AssessmentItem;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.AssociateInteraction;
import uk.ac.ed.ph.jqtiplus.node.item.interaction.Interaction;

class AssociateStrategyTest {

   private AssessmentItem       assessmentItem;

   private ItemBody             itemBody;

   private AssociateInteraction associateInteraction;

   private List<Interaction>    interactions;

   @BeforeEach
   public void initAssessmentItem() {

      assessmentItem = QtiUtil.extractAssessmentItem("associate.xml");

      itemBody = assessmentItem.getItemBody();

      interactions = itemBody.findInteractions();
      Interaction interaction = interactions.get(0);

      associateInteraction = (AssociateInteraction) interaction;

   }

   @Test
   void interactionsSizeTest() {
      assertEquals(1, interactions.size());
   }

   @Test
   void testParse() {

      AssociateStrategy strategy = new AssociateStrategy();
      Question question = strategy.parse(associateInteraction);

      assertEquals("RESPONSE", question.getId());

      assertEquals("associateInteraction", question.getType());

      assertEquals(6, question.getAnswers().size());

      List<Answer> answers = question.getAnswers();
      Answer answer0 = answers.get(0);

      assertAll("answer0", 
    		  () -> assertEquals("A", answer0.getId()), // TODO:
    		  () -> assertEquals("Antonio", answer0.getText()), 
    		  () -> assertEquals(false, answer0.isCorrect())// !!!!!
      );

      Answer answer1 = answers.get(1);

      assertAll("answer1", 
    		  () -> assertEquals("C", answer1.getId()), // TODO:
    		  () -> assertEquals("Capulet", answer1.getText()), 
    		  () -> assertEquals(false, answer1.isCorrect())// !!!!!
      );

      Answer answer2 = answers.get(2);

      assertAll("answer2", 
    		  () -> assertEquals("D", answer2.getId()), // TODO:
    		  () -> assertEquals("Demetrius", answer2.getText()), 
    		  () -> assertEquals(false, answer2.isCorrect())// !!!!!
      );

      Answer answer3 = answers.get(3);

      assertAll("answer3", 
    		  () -> assertEquals("L", answer3.getId()), // TODO:
    		  () -> assertEquals("Lysander", answer3.getText()),
    		  () -> assertEquals(false, answer3.isCorrect())// !!!!!
      );

      Answer answer4 = answers.get(4);

      assertAll("answer4", 
    		  () -> assertEquals("M", answer4.getId()), // TODO:
    		  () -> assertEquals("Montague", answer4.getText()), 
    		  () -> assertEquals(false, answer4.isCorrect())// !!!!!
      );

      Answer answer5 = answers.get(5);

      assertAll("answer5", 
    		  () -> assertEquals("P", answer5.getId()), // TODO:
    		  () -> assertEquals("Prospero", answer5.getText()),
    		  () -> assertEquals(false, answer5.isCorrect())// !!!!!
      );
   }
}
