package model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Scoreboard {
	private Pane root;
	private int[] scores;
	private Text text;
	private int posX = 500;
	private int posY = 20;
	private int color_val = 1;

	public static String make_scoreboard(int[] s) {
		String acc = "";
		if (s.length != 0) {
			acc += s[0];
			for (int i = 1; i < s.length; i++) {
				acc += " : " + s[i];
			}
		}
		return acc;
	}

	public Scoreboard (Pane root, int n) {
		this.root = root;
		this.scores = new int[n];
		this.text = new Text();
		this.text.setX(posX);
		this.text.setY(posY);
		this.text.setFont(new Font(20));
		this.text.setText(make_scoreboard(scores));
		root.getChildren().add(this.text);
	}
	
	public int get_color_val() {
		return this.color_val;
	}

	public Text get_text() {
		return this.text;
	}
	
	public void render() {
		this.text.setVisible(true);
	}
	public void no_render() {
		this.text.setVisible(false);
	}
	public void update_render(Color c) {
		this.text.setText(make_scoreboard(scores));
		this.text.setFill(c);
	}
	
	public boolean add_point(int i) { // j'utilise un booleen pour signaler si une erreur s'est produite
		if (i < 0 || i >= scores.length) {
			return false;
		}
		scores[i] += 1;
		return true;
	}
	
	public boolean set_scores(int[] s) {
		if (scores.length != s.length) {
			return false;
		}
		for (int i = 0; i < s.length; i++) {
			scores[i] = s[i];
		}
		return true;
	}
}
