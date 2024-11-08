package view;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AnswerWrong implements StrategyAnswer {
	public void displayEffect(QuestionSP spQuestion) {
		//We get the board
		BoardBP board = (BoardBP)spQuestion.getParent().getParent();
		
		//We hide the question, stop the timer and display the message saying that the answer was incorrect
		spQuestion.setVisible(false);
		spQuestion.getTimer().stop();
		board.getTxtAnswer().setText("This is not the correct answer !");
		DropShadow ds = new DropShadow();
		ds.setOffsetY(4.0f);
		ds.setColor(Color.BLACK);
		board.getTxtAnswer().setEffect(ds);
		board.getTxtAnswer().setVisible(true);
		
		//We put the challenge to false in case the pawn was on a challenge case
		board.setChallenge(false);
		
		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		pause.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//We pass the hand to the next player
				board.nextPlayer();
				//we hide the message
				board.getTxtAnswer().setVisible(false);
				//The difficulty is re-displayed
				board.displayCard();
			}
		});
		pause.play();
	}
}
