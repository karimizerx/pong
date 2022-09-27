
package model;

import javafx.scene.text.*;
import javafx.scene.layout.Pane;

public class Scoreboard{
	private Pane root;
	private int[] scores;
	private Text text;
	private int posX = 500;
	private int posY = 20;

	public static String makeScoreboard(int[] s){
		String acc = "";
		for(int i = 0; i < s.length; i++){
			if(i != 0)
				acc += " : ";
			acc += s[i];
		}
		return acc;
	}

	public Scoreboard(Pane root, int n){
		this.root = root;
		this.scores = new int[n];
		this.text = new Text();
		this.text.setX(posX);
		this.text.setY(posY);
		this.text.setFont(new Font(20));
		this.text.setText(makeScoreboard(scores));
		root.getChildren().add(this.text);
	}
	
	public Text getText(){ return this.text; }
	
	public void render(){
		this.text.setText(makeScoreboard(scores));
	}
	
	public boolean addPoint(int i){ // j'utilise un booleen pour signaler si une erreur s'est produite
		if(i < 0 || i >= scores.length)
			return false;
		scores[i] += 1;
		render();
		return true;
	}
	
	public boolean setScores(int[] s){
		if(scores.length != s.length)
			return false;
		for(int i = 0; i < s.length; i++){
			scores[i] = s[i];
		}
		render();
		return true;
	}
}