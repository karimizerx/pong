package model;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;

import game_objects.Racket;
import game_objects.Ball;
import gui.GameView;

public class Court {
	// instance parameters
	private final Racket playerA, playerB;
	private double width, height; // m
	private double racketSpeed = 150.0; // m/s
	private final Ball ball;
	private Color primaire;
	private Color secondaire;

	java.util.LinkedList<gamemodes.Gamemode> gamemodes;

	private Scoreboard scoreboard;

	public Court(Pane root, Racket playerA, Racket playerB, double width, double height,
			java.util.LinkedList<gamemodes.Gamemode> gamemodes, Color prim, Color secon) {
		this.playerA = playerA;
		this.playerB = playerB;
		this.ball = new Ball(root);
		this.width = width;
		this.height = height;
		this.scoreboard = new Scoreboard(root, 2);
		this.gamemodes = gamemodes;
		this.primaire = prim;
		this.secondaire = secon;
		reset();
	}

	public Color getSecondaire() {
		return this.secondaire;
	}

	public Racket getPlayerA() {
		return playerA;
	}

	public Racket getPlayerB() {
		return playerB;
	}

	public Ball getBall() {
		return ball;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void setWidth(double v) {
		width = v;
	}

	public void setHeight(double v) {
		height = v;
	}

	public double getRacketSpeed() {
		return racketSpeed;
	}

	public void addRacketSpeed(double v){
		racketSpeed += v;
	}
	
	public Scoreboard getScoreboard() {
		return scoreboard;
	}

	public void on_key_pressed(KeyCode key) {
		playerA.on_key_pressed(key);
		playerB.on_key_pressed(key);
		for (gamemodes.Gamemode gamemode : gamemodes) {
			gamemode.on_key_pressed(key);
		}
	}

	public void on_key_released(KeyCode key) {
		playerA.on_key_released(key);
		playerB.on_key_released(key);
		for (gamemodes.Gamemode gamemode : gamemodes) {
			gamemode.on_key_released(key);
		}
	}

	public void update(double deltaT) {
		for (gamemodes.Gamemode gamemode : gamemodes) {
			gamemode.update(this, deltaT);
		}
		playerA.update(this, deltaT);
		playerB.update(this, deltaT);
		if (ball.update(this, deltaT)) {
			reset();
		}
	}

	public Color getColor(int o) {
		if (o == 1) {
			return primaire;
		} else
			return secondaire;
	}

	public void render(GameView view) {
		for (gamemodes.Gamemode gamemode : gamemodes) {
			gamemode.render(view, this);
		}
		ball.render(view, this, getColor(ball.getColorVal()));
		playerA.render(view, this, getColor(playerA.getColorVal()));
		playerB.render(view, this, getColor(playerB.getColorVal()));
		scoreboard.render(getColor(1));
	}

	void reset() {
		for (gamemodes.Gamemode gamemode : gamemodes) {
			gamemode.reset();
		}
		this.playerA.reset(this);
		this.playerB.reset(this);
		this.ball.reset(this);
		this.racketSpeed = 150.0;
	}
}
