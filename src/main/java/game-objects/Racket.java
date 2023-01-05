package game_objects;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import gui.GameView;
import model.Court;
import model.Ressources;

public class Racket extends GameObject {
	private KeyCode up_key;
	private KeyCode down_key;
	private double direction; // -1 is up, 1 is down, 0 is idle, can be multiplied by x to indicate a speed factor.
	private double rel_x;

	public Racket(Pane root, KeyCode up_key, KeyCode down_key, double x, double y, double w, double h) {
		super(x, y, w, h, root, Ressources.get_image("racket_" + (x >= 0 ? "l" : "r"))); // This initial x might be inaccurate (if x < 0), that will be fixed on the first call to update.
		this.rel_x = x;
		direction = 0;

		this.up_key = up_key;
		this.down_key = down_key;
	}

	public void set_height(double v) {
		if (v > 5) { super.set_height(v); }
	}

	public void set_down_key(KeyCode k) {
		down_key = k;
	}
	public void set_up_key(KeyCode k) {
		up_key = k;
	}
	public KeyCode get_up_key() {
		return up_key;
	}
	public KeyCode get_down_key() {
		return down_key;
	}


	public void set_direction(double v) {
		direction = v;
	}

	public void on_key_pressed(KeyCode key) {
		if (key == up_key) {
			direction = -1;
		} else if (key == down_key) {
			direction = 1;
		}
	}

	public void on_key_released(KeyCode key) {
		if (key == up_key && direction < 0) {
			direction = 0;
		} else if (key == down_key && direction > 0) {
			direction = 0;
		}
	}

	public void update(Court court, double dt) {
		set_x(rel_x >= 0 ? rel_x : court.get_width() + rel_x);
		set_vel(0, direction * court.get_racket_speed());
		super.update(court, dt);
		if (get_up() < 0) {
			change_y(0 - get_up());
		}
		if (get_down() > court.get_height()) {
			change_y(court.get_height() - get_down());
		}
	}

	public void reset(Court court) {
		set_x(rel_x >= 0 ? rel_x : court.get_width() + rel_x);
		set_y(court.get_height() / 2);
		direction = 0;
	}
}
