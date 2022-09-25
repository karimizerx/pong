package model; 

import javafx.scene.text.*; 

public class Scoreboard{
	private int[] scores;
	private Text text;
	private int posX = 500;
	private int posY = 800;
	public static String makeScoreboard(int[] s){
		String acc = "";
		for(int i = 0; i < s.length; i++){
			if(i != 0)
				acc += " : ";
			acc += s[i];
		}
		return acc;
	}
	
	public Scoreboard(int n){
		this.scores = new int[n];
		this.text = new Text();
		this.text.setX(posX);
		this.text.setY(posY);
		this.text.setFont(new Font(100));
		this.text.setText(makeScoreboard(scores));
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


