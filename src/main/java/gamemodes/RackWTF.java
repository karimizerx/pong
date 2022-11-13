package gamemodes;

import javafx.scene.input.KeyCode;

public class RackWTF implements Gamemode {
	static float min_length = 1;
	static float max_length = 250;
	static int wtf = 50;

	public void reset() {}

	public void on_key_pressed(KeyCode key) {
		if (key == KeyCode.R) {
			reset();
		}
	}

	public void on_key_released(KeyCode key) {}
	public void on_ball_touched_racket(model.Court court, boolean left) {}
	public void on_ball_left_terrain(model.Court court, boolean left) {}
	
	private double random(double min, double max) {
		return Math.random() * (max - min + 1) + min;
	}

	public void update(model.Court court, double dt) {
		double add_length_a;
		double add_length_b;

		if (court.get_player_a().get_height() > min_length && court.get_player_a().get_height() < max_length) {
			add_length_a = random(-wtf, wtf);
		} else if (court.get_player_a().get_height() < min_length) {
			add_length_a = random(0, wtf);
		} else {
			add_length_a = random(-wtf, 0);
		}

		if (court.get_player_b().get_height() > min_length && court.get_player_b().get_height() < max_length) {
			add_length_b = random(-wtf, wtf);
		} else if (court.get_player_b().get_height() < min_length) {
			add_length_b = random(0, wtf);
		} else {
			add_length_b = random(-wtf, 0);
		}

		court.get_player_a().set_height(court.get_player_a().get_height() + add_length_a);
		court.get_player_b().set_height(court.get_player_b().get_height() + add_length_b);
		return;
	}

	public void render(gui.GameView view, model.Court court) {}
}
