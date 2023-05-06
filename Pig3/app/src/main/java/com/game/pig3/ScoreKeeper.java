package com.game.pig3;



import android.widget.TextView;

public class ScoreKeeper
{
public static String score_string;
private static int currentTurnScore;
private static TextView currentTurnText;
private static TextView p1text;
private static TextView p2text;

public ScoreKeeper() {


	currentTurnScore = 0;
	p1text = MainActivity.getP1Text();
	p2text = MainActivity.getP2Text();
	currentTurnText = MainActivity.getCurrentTurnText();
	updateScoresText();
}

public static void reset() {
	resetScores();
	updateScoresText();
	SaveManager.save();
	new PlayerWindow();
}

private static void resetScores() {
	setCurrentTurnScore(0);
	MainActivity.getPlayer(1).setScore(0);
	MainActivity.getPlayer(2).setScore(0);
}
//
private static void updateScoresText() {
	if (MainActivity.getCurrentPlayer()!= null)
	{
		currentTurnText.setText("Current player: " +MainActivity.getCurrentPlayer().getName() + "       Current Turn Points: "+ currentTurnScore);

	}else
	{
		currentTurnText.setText(Integer.toString(currentTurnScore));
	}
	if (MainActivity.getPlayer(1) != null) {
		p1text.setText(MainActivity.getPlayer(1).getName() + ": "+ MainActivity.getPlayer(1).getScore());
		if (MainActivity.getCurrentPlayer() == MainActivity.getPlayer(1))
		{
			//TODO: p1text.setBackground(Color.orange);
		}else {
			// TODO: p1text.setBackground(Color.white);
		}
	}
	if (MainActivity.getPlayer(2) != null){
		p2text.setText(MainActivity.getPlayer(2).getName() + ": "+ MainActivity.getPlayer(2).getScore());
		if (MainActivity.getCurrentPlayer() == MainActivity.getPlayer(2))
		{
			//TODO: p2text.setBackground(Color.orange);
		}else {
			//TODO: p2text.setBackground(Color.white);
		}
	}
}

public static void setTextWithString(String s) {
	currentTurnText.setText(s);
}

public static void setCurrentTurnScore(int currentTurnScore) {
	ScoreKeeper.currentTurnScore = currentTurnScore;
}

public static void setScoresfromSave(int savedScore1, int savedScore2) {
	MainActivity.getPlayer(1).setScore(savedScore1);
	MainActivity.getPlayer(2).setScore(savedScore2);
	updateScoresText();
}
public static void addToCurrentScore(int rolledValueSum) {
	currentTurnScore += rolledValueSum;
	updateScoresText();
}

public static void endRun(boolean keepPoints) {
	if (keepPoints) {
		Player player = MainActivity.getCurrentPlayer();
		player.addPoints(currentTurnScore);
		if (player.getScore() >= 100) {
			reset();
			setTextWithString(player.getName() + " WINS");
	}

		currentTurnScore = 0;
		updateScoresText();
	} else {
		currentTurnScore = 0;
		updateScoresText();
		setTextWithString(MainActivity.getCurrentPlayer().getName() + " Rolled a 1! :( ");
	}

	SaveManager.save();
	MainActivity.switchCurrentPlayer();
}
}
