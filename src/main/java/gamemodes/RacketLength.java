package gamemodes;

import javafx.scene.input.KeyCode;

public class RacketLength implements Gamemode {
	private double speed_a;
	private double speed_b;
	private double max_length = 200;
	private double min_length = 10;

	public RacketLength() {
		this(1);
	}

	public RacketLength(double speed) {
		this.speed_a = speed;
		this.speed_b = speed;
	}

	public String getName() {
		return "Racket Length";
	}

	public void reset() {}

	public void on_key_pressed(KeyCode key) {}
	public void on_key_released(KeyCode key) {}
	public void on_ball_touched_racket(model.Court court, boolean left) {}
	public void on_ball_left_terrain(model.Court court, boolean left) {}

	public void update(model.Court court, double dt) {
		if ((court.get_player_a().get_height() > max_length && speed_a > 0)  || (court.get_player_a().get_height() < min_length && speed_a < 0)) {
			speed_a *= -1;
		}
		if ((court.get_player_b().get_height() > max_length && speed_b > 0)  || (court.get_player_b().get_height() < min_length && speed_b < 0)) {
			speed_b *= -1;
		}

		court.get_player_a().set_height(court.get_player_a().get_height() + speed_a);
		court.get_player_b().set_height(court.get_player_b().get_height() + speed_b);
	}

	public void render() {}
	public void no_render() {}
	public void update_render(gui.GameView view, model.Court court) {}
}
