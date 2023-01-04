package model;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import game_objects.Ball;
import game_objects.Racket;
import gui.GameView;

public class Court {
	// instance parameters
	private final Racket player_a, player_b;
	private double width, height; // m
	private double racket_speed = 250; // m/s
	private final Ball ball;
	private KeyCode pauseKey;
	private boolean paused = false;

	private double last_width;

	java.util.LinkedList<gamemodes.Gamemode> gamemodes;

	public Court(Pane root, Racket player_a, Racket player_b, double width, double height,
	             java.util.LinkedList<gamemodes.Gamemode> gamemodes, KeyCode pauseKey) {
		this.player_a = player_a;
		this.player_b = player_b;
		this.ball = new Ball(root);
		this.width = width;
		this.last_width = width;
		this.height = height;
		this.gamemodes = gamemodes;
		this.pauseKey = pauseKey;
		root.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundImage(
			Ressources.get_image("background"),
			javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
			javafx.scene.layout.BackgroundRepeat.NO_REPEAT,
			javafx.scene.layout.BackgroundPosition.CENTER,
			new javafx.scene.layout.BackgroundSize(javafx.scene.layout.BackgroundSize.AUTO, 1, false, true, false, true)
		)));
		reset();
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
		if(key == pauseKey)
			togglePause();
	}

	public void togglePause(){
		paused = !paused;
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
		double mult = get_width() / last_width;
		last_width = get_width();
		ball.scale_vel(mult, mult);
		racket_speed *= mult;

		if (!paused) {
			for (gamemodes.Gamemode gamemode : gamemodes) {
				gamemode.update(this, dt);
			}
			player_a.update(this, dt);
			player_b.update(this, dt);
			ball.update(this, dt);
		}
	}

	public void render(GameView view) {
		for (gamemodes.Gamemode gamemode : gamemodes) {
			gamemode.update_render(view, this);
		}
		ball.render(view, this);
		player_a.render(view, this);
		player_b.render(view, this);
	}

	void reset() {
		for (gamemodes.Gamemode gamemode : gamemodes) {
			gamemode.reset();
		}
		this.player_a.reset(this);
		this.player_b.reset(this);
		this.ball.reset(this);
		this.racket_speed = 250 * get_width() / 600;
		this.ball.scale_vel(get_width() / 1000, get_width() / 1000);
	}
}
