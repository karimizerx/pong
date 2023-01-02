package gamemodes;

import javafx.scene.input.KeyCode;

import model.Court;

public class Ia implements Gamemode {
	private double difficulty;
	private boolean gauche;

	public Ia(double diff, boolean g) {
		difficulty = diff;
		gauche = g;
	}

	public String getName() {
		return "IA (" + (gauche ? "gauche" : "droite") + ")";
	}

	public void on_key_pressed(KeyCode key) {}
	public void on_key_released(KeyCode key) {}
	public void on_ball_touched_racket(model.Court court, boolean left) {}
	public void on_ball_left_terrain(model.Court court, boolean left) {}

	public void update(model.Court court, double dt) {
		var player = gauche ? court.get_player_a() : court.get_player_b();
		var ball = court.get_ball();
		if (difficulty <= 3) {
			if (ball.get_middle_y() > player.get_middle_y()) {
				player.set_direction(+0.25 + difficulty * 0.25);
			} else {
				player.set_direction(-0.25 - difficulty * 0.25);
			}
		} else if (difficulty == 4) {
			player.set_direction((ball.get_middle_y() - player.get_middle_y()) / court.get_racket_speed() / 0.01);
		}
	}

	public void reset() {}

	public void render() {}
	public void no_render() {}
	public void update_render(gui.GameView view, Court court) {}
}
