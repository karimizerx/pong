package gamemodes;

import javafx.scene.input.KeyCode;

public class Acceleration implements Gamemode {
	double accel_ball = 1.1;
	double accel_racket = 1;
	double v = 1;

	public Acceleration() {}

	public void reset() {}

	public void on_key_pressed(KeyCode key) {}
	public void on_key_released(KeyCode key) {}

	public void update(model.Court court, double dt) {
		if (v * court.get_ball().get_dx() < 0) {
			court.get_ball().scale_vel(accel_ball, accel_ball);
			court.add_racket_speed(accel_racket);
			v = court.get_ball().get_dx();
		}
	}

	public void render(gui.GameView view, model.Court court) {}
}
