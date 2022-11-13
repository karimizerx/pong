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
	private final Racket player_a, player_b;
	private double width, height; // m
	private double racket_speed = 150.0; // m/s
	private final Ball ball;
	private Color primaire;
	private Color secondaire;

	java.util.LinkedList<gamemodes.Gamemode> gamemodes;

	public Court(Pane root, Racket player_a, Racket player_b, double width, double height,
			java.util.LinkedList<gamemodes.Gamemode> gamemodes, Color prim, Color secon) {
		this.player_a = player_a;
		this.player_b = player_b;
		this.ball = new Ball(root);
		this.width = width;
		this.height = height;
		this.gamemodes = gamemodes;
		this.primaire = prim;
		this.secondaire = secon;
		reset();
	}

	public Color get_primaire() {
		return this.primaire;
	}

	public Color get_secondaire() {
		return this.secondaire;
	}

	public Racket get_player_a() {
		return player_a;
	}

	public Racket get_player_b() {
		return player_b;
	}

	public Ball get_ball() {
		return ball;
	}

	public double get_width() {
		return width;
	}

	public double get_height() {
		return height;
	}

	public void set_width(double v) {
		width = v;
	}

	public void set_height(double v) {
		height = v;
	}

	public double get_racket_speed() {
		return racket_speed;
	}

	public void add_racket_speed(double v) {
		racket_speed += v;
	}
	
	public void on_key_pressed(KeyCode key) {
		player_a.on_key_pressed(key);
		player_b.on_key_pressed(key);
		for (gamemodes.Gamemode gamemode : gamemodes) {
			gamemode.on_key_pressed(key);
		}
	}

	public void on_key_released(KeyCode key) {
		player_a.on_key_released(key);
		player_b.on_key_released(key);
		for (gamemodes.Gamemode gamemode : gamemodes) {
			gamemode.on_key_released(key);
		}
	}

	public void on_ball_touched_racket(boolean left) {
		for (gamemodes.Gamemode gamemode : gamemodes) {
			gamemode.on_ball_touched_racket(this, left);
		}
	}
	public void on_ball_left_terrain(boolean left) {
		for (gamemodes.Gamemode gamemode : gamemodes) {
			gamemode.on_ball_left_terrain(this, left);
		}
		reset();
	}

	public void update(double dt) {
		for (gamemodes.Gamemode gamemode : gamemodes) {
			gamemode.update(this, dt);
		}
		player_a.update(this, dt);
		player_b.update(this, dt);
		ball.update(this, dt);
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
		ball.render(view, this, getColor(ball.get_color_val()));
		player_a.render(view, this, getColor(ball.get_color_val()));
		player_b.render(view, this, getColor(ball.get_color_val()));
	}

	void reset() {
		for (gamemodes.Gamemode gamemode : gamemodes) {
			gamemode.reset();
		}
		this.player_a.reset(this);
		this.player_b.reset(this);
		this.ball.reset(this);
		this.racket_speed = 150.0;
	}

}
