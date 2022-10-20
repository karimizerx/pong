
package model;

import javafx.scene.text.*;

import javax.swing.text.AttributeSet.ColorAttribute;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Scoreboard {
	private Pane root;
	private int[] scores;
	private Text text;
	private int posX = 500;
	private int posY = 20;
	private int colorval = 1;

	public static String makeScoreboard(int[] s) {
		String acc = "";
		for (int i = 0; i < s.length; i++) {
			if (i != 0)
				acc += " : ";
			acc += s[i];
		}
		return acc;
	}

	public Scoreboard(Pane root, int n) {
		this.root = root;
		this.scores = new int[n];
		this.text = new Text();
		this.text.setX(posX);
		this.text.setY(posY);
		this.text.setFont(new Font(20));
		this.text.setText(makeScoreboard(scores));
		root.getChildren().add(this.text);
	}

	public int getColorVal() {
		return this.colorval;
	}

	public Text getText() {
		return this.text;
	}

	public void render(Color c) {
		this.text.setText(makeScoreboard(scores));
		this.text.setFill(c);
	}

	public boolean addPoint(int i) { // j'utilise un booleen pour signaler si une erreur s'est produite
		if (i < 0 || i >= scores.length) {
			return false;
		}
		scores[i] += 1;
		return true;
	}

	public boolean setScores(int[] s) {
		if (scores.length != s.length) {
			return false;
		}
		for (int i = 0; i < s.length; i++) {
			scores[i] = s[i];
		}
		return true;
	}
}
