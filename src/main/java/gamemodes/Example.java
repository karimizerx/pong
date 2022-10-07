package gamemodes;

import javafx.scene.input.KeyCode;

public class Example implements Gamemode {
	double gravity;

	public Example() {
		reset();
	}

	public void reset() {
		gravity = 10;
	}
	public void on_key_pressed(KeyCode key) {
		if (key == KeyCode.G) {
			gravity = -gravity;
		}
	}
	public void on_key_released(KeyCode key) {
	}
	public void update(model.Court court, double deltaT) {
		court.getBall().acc_y(gravity);
	}
	public void render(gui.GameView view, model.Court court) {
	}
}
