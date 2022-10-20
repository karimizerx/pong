package gamemodes;

import javafx.scene.input.KeyCode;

public class RacketLength implements Gamemode {
	private double speed = 1;
	private double speed_a = speed;
	private double speed_b = speed;
	private double max_length = 200;
	private double min_length = 10;

	public RacketLength() {}

	public RacketLength(double speed) {
		this.speed = speed;
		this.speed_a = speed;
		this.speed_b = speed;
	}

	public void reset() {}

	public void on_key_pressed(KeyCode key) {}
	public void on_key_released(KeyCode key) {}

	public void update(model.Court court, double dt) {
		if (court.get_player_a().get_height() > max_length || court.get_player_a().get_height() < min_length) {
			speed_a *= -1;
		}
		if (court.get_player_b().get_height() > max_length || court.get_player_b().get_height() < min_length) {
			speed_b *= -1;
		}
	
		court.get_player_a().add_height(speed_a);
		court.get_player_a().add_y(-speed_a / 2);
		court.get_player_b().add_height(speed_b);
		court.get_player_b().add_y(-speed_b / 2);
	}

	public void render(gui.GameView view, model.Court court) {}
}
