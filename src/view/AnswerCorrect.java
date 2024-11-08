package view;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Pawn;
import model.PawnManagement;

public class AnswerCorrect implements StrategyAnswer {
	public void displayEffect(QuestionSP spQuestion) {
		//We get the boardBP
		BoardBP board = (BoardBP)spQuestion.getParent().getParent();
		//We stop the timer
		spQuestion.getTimer().stop();
		//We get the movement based on the difficulty of the question
		int movement = 0;
		movement = board.getCurrentCard().find(spQuestion.getQuestion()) + 1;
		//We get the pawn that needs to be moved
		Pawn pawn = board.getCurrentPawn();
		PawnManagement pawns = board.getPawns();
		//We check if it is not on a challenge case
		if(board.isChallenge()) {
			//We move the pawn from twice the movement
			pawns.movePawn(pawn, movement * 2);
			board.setChallenge(false);
		} else {
			pawns.movePawn(pawn, movement);
		}
		board.displayPawn();
		//We check if a player won the game
		if(pawns.find(pawns.find(pawn)).getCasePawn() == GraphicConstants.VICTORY) {
			//Modifying the Victory text to know which player won
			board.getTxtWinner().setText(board.getTxtWinner().getText().replace('?', Character.forDigit(board.getPawns().find(board.getCurrentPawn())+1, 10)));
			board.getSpQuestion().setVisible(false);
			board.getVbWinTop().setVisible(true);
			board.getVbWinBottom().setVisible(true);
		} else {
			//We hide the question and display the message saying that the answer is correct
			spQuestion.setVisible(false);
			board.getTxtAnswer().setText("This is the correct answer !");
			DropShadow ds = new DropShadow();
			ds.setOffsetY(4.0f);
			ds.setColor(Color.BLACK);
			board.getTxtAnswer().setEffect(ds);
			board.getTxtAnswer().setVisible(true);
			
			//A PauseTransition is created to display the message only for a certain time here 2 seconds
			PauseTransition pause = new PauseTransition(Duration.seconds(3));
			pause.setOnFinished(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					//We pass to the next pawn
					board.nextPlayer();
					//We hide the message
					board.getTxtAnswer().setVisible(false);
					//The difficulty is re-displayed
					board.displayCard();
				}
			});
			//Launch the PauseTransition
			pause.play();
		}
	}
}
