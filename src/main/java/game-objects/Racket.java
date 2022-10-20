package game_objects;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import model.Court;
import game_objects.Ball;
import gui.GameView;
import game_objects.GameObject;

public class Racket implements GameObject {
	private KeyCode up_key;
	private KeyCode down_key;
	private int direction; // -1 is up, 1 is down, 0 is idle
	private double rel_x;
	private double x;
	private double y;
	private double w;
	private double h;
	private int color_val = 1;
	private Rectangle rectangle;

	public Racket(Pane root, KeyCode up_key, KeyCode down_key, double x, double y, double w, double h) {
		this.up_key = up_key;
		this.down_key = down_key;
		direction = 0;
		this.rel_x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		rectangle = new Rectangle();
		root.getChildren().add(rectangle);
	}

	public int get_color_val() {
		return this.color_val;
	}

	public double get_height() {
		return h;
	}
	public void set_height(double h) {
		this.h = h;
	}
	public void add_height(double h) {
		this.h += h;
	}

	public double get_width() {
		return w;
	}
	public void set_width(double w) {
		this.w = w;
	}
	public void add_width(double w) {
		this.w += w;
	}

	public double get_left() {
		return x;
	}

	public double get_right() {
		return x + w;
	}

	public double get_up() {
		return y;
	}

	public double get_down() {
		return y + h;
	}

	public double get_middle_x() {
		return x + w / 2;
	}
	public void set_x(double x) {
		this.x = x;
	}
	public void add_x(double x) {
		this.x += x;
	}

	public double get_middle_y() {
		return y + h / 2;
	}
	public void set_y(double y) {
		this.y = y;
	}
	public void add_y(double y) {
		this.y += y;
	}

	public void on_key_pressed(KeyCode key) {
		if (key == up_key) {
			direction = -1;
		} else if (key == down_key) {
			direction = 1;
		}
	}

	public void on_key_released(KeyCode key) {
		if (key == up_key && direction == -1) {
			direction = 0;
		} else if (key == down_key && direction == 1) {
			direction = 0;
		}
	}

	public void update(Court court, double dt) {
		y += direction * dt * court.get_racket_speed();
		if (y < 0) {
			y = 0;
		}
		if (y + h > court.get_height()) {
			y = court.get_height() - h;
		}
	}

	public void reset(Court court) {
		y = (court.get_height() - h) / 2;
	}

	public void render(GameView view, Court court, Color c) {
		rectangle.setHeight(h * view.get_scale());
		rectangle.setWidth(w * view.get_scale());
		rectangle.setFill(c);

		x = rel_x >= 0 ? rel_x : court.get_width() + rel_x;
		rectangle.setX(x * view.get_scale());
		rectangle.setY(y * view.get_scale());
	}
}
