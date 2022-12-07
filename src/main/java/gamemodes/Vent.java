package gamemodes;

import javafx.scene.input.KeyCode;
import librairies.OpenSimplexNoise;
import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Vent implements Gamemode {
	private static class Particle {
		double x;
		double y;
		double dx;
		double dy;
		Circle circle;
	}

	Random random = new Random();
	OpenSimplexNoise noise = new OpenSimplexNoise();
	double t = random.nextInt(100000000);
	ArrayList<Particle> particles;

	public Vent(Pane root) {
		particles = new ArrayList<Particle>(10000);
		for (int i = 0; i < 10000; i++) {
			Particle p = new Particle();
			p.circle = new Circle();
			p.circle.setRadius(2);
			p.circle.setFill(javafx.scene.paint.Color.color(0.0f, 0.0f, 1.0f, 0.1f));
			root.getChildren().add(p.circle);
			particles.add(p);
		}
	}

	public void reset() {
		t = random.nextInt(100000000);
		for (Particle p : particles) {
			p.dx = 0;
			p.dy = 0;
			p.x = -10; // The negative values here mean that the position will be
			p.y = -10; // reset in update, which will allow us to use the court size.
			p.circle.setCenterX(p.x);
			p.circle.setCenterY(p.y);
		}
	}

	public void on_key_pressed(KeyCode key) {}
	public void on_key_released(KeyCode key) {}
	public void on_ball_touched_racket(model.Court court, boolean left) {}
	public void on_ball_left_terrain(model.Court court, boolean left) {}

	public void update(model.Court court, double dt) {
		game_objects.Ball b = court.get_ball();
		final double θ   = noise.eval(t + 0000, b.get_middle_x() / 500, b.get_middle_y() / 500) * Math.PI * 4; // (∈ [-4π; 4π])
		final double mag = noise.eval(t + 9999, b.get_middle_x() / 500, b.get_middle_y() / 500) + 0.75; // (∈ [0.25; 1.25])
		final double x = mag * Math.cos(θ);
		final double y = mag * Math.sin(θ);
		b.apply_force(x * 2, y);
		t += 0.003;
		for (Particle p : particles) {
			// Every particle has a 1/100 chance of being reset.
			if (p.x < 0 || p.y < 0 || p.x >= court.get_width() || p.y >= court.get_height() || random.nextInt(100) < 1) {
				p.dx = 0;
				p.dy = 0;
				p.x = random.nextDouble() * court.get_width();
				p.y = random.nextDouble() * court.get_height();
			}
			final double p_θ   = noise.eval(t + 0000, p.x / 500, p.y / 500) * Math.PI * 4; // (∈ [-4π; 4π])
			final double p_mag = noise.eval(t + 9999, p.x / 500, p.y / 500) + 0.75; // (∈ [0.25; 1.25])
			final double p_x = p_mag * Math.cos(p_θ);
			final double p_y = p_mag * Math.sin(p_θ);
			p.dx += p_x / 5;
			p.dy += p_y / 10;
			p.x += p.dx;
			p.y += p.dy;
			p.circle.setCenterX(p.x);
			p.circle.setCenterY(p.y);
		}
	}

	public void render() {
		for (Particle p : particles) {
			p.circle.setVisible(true);
		}
	}
	public void no_render() {
		for (Particle p : particles) {
			p.circle.setVisible(false);
		}
	}
	public void update_render(gui.GameView view, model.Court court) {}
}
