package gamemodes;

import javafx.scene.input.KeyCode;
import librairies.OpenSimplexNoise;
import java.util.Random;
import java.lang.Math;

public class Vent implements Gamemode {
	Random random = new Random();
	OpenSimplexNoise noise = new OpenSimplexNoise();
	double t = random.nextInt(100000000);

	public void reset() {
		t = random.nextInt(100000000);
	}

	public void on_key_pressed(KeyCode key) {}
	public void on_key_released(KeyCode key) {}
	public void on_ball_touched_racket(model.Court court, boolean left) {}
	public void on_ball_left_terrain(model.Court court, boolean left) {}

	public void update(model.Court court, double dt) {
		game_objects.Ball b = court.get_ball();
		final double θ   = noise.eval(t + 0000, b.get_middle_x(), b.get_middle_y()) * Math.PI; // (∈ [-π; π])
		final double mag = noise.eval(t + 9999, b.get_middle_x(), b.get_middle_y()) + 1; // (∈ [ 0; 2])
		final double x = mag * Math.cos(θ);
		final double y = mag * Math.sin(θ);
		b.apply_force(x, y);
		t += 0.003;
	}

	public void render(gui.GameView view, model.Court court) {}
}
