package gamemodes;

import javafx.scene.input.KeyCode;

public class Acceleration implements Gamemode {
	double accel_ball = 1.05;
	double accel_racket = 1;

	public Acceleration() {}

	public String getName() {
		return "Acceleration";
	}

	public void reset() {}

	public void on_key_pressed(KeyCode key) {}
	public void on_key_released(KeyCode key) {}
	public void on_ball_left_terrain(model.Court court, boolean left) {}
	public void update(model.Court court, double dt) {}

	public void on_ball_touched_racket(model.Court court, boolean left) {
		if (court.get_ball().get_dx() < 800) {
			court.get_ball().scale_vel(accel_ball, accel_ball);
			court.add_racket_speed(accel_racket);
		}
	}

	public void render() {}
	public void no_render() {}
	public void update_render(gui.GameView view, model.Court court) {}
}
