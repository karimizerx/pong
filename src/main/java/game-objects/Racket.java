package game_objects;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import model.Court;
import game_objects.Ball;
import gui.GameView;
import game_objects.GameObject;

public class Racket extends GameObject {
	private KeyCode up_key;
	private KeyCode down_key;
	private double direction; // -1 is up, 1 is down, 0 is idle, can be multiplied by x to indicate a speed
								// factor.
	private double rel_x;
	private int color_val = 1;
	private Rectangle rectangle;

	public Racket(Pane root, KeyCode up_key, KeyCode down_key, double x, double y, double w, double h, Image skin) {
		super(x, y, w, h, skin); // This initial x might be inaccurate, we'll see.
		this.rel_x = x;
		direction = 0;

		this.up_key = up_key;
		this.down_key = down_key;

		rectangle = new Rectangle();
		if (skin != null)
			rectangle.setFill(new ImagePattern(skin));
		root.getChildren().add(rectangle);
	}

	public void set_height(double v) {
		if (v > 5) { super.set_height(v); }
	}
	public int get_color_val() {
		return this.color_val;
	}

	public void set_down_key(KeyCode k){
		down_key=k;
	}
	public void set_up_key(KeyCode k){
		up_key=k;
	}
	public KeyCode get_up_key(){
		return up_key;
	}
	public KeyCode get_down_key(){
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
	}

	public void render(GameView view, Court court, Color c) {
		set_x(rel_x >= 0 ? rel_x : court.get_width() + rel_x);

		rectangle.setX(get_left() * view.get_scale());
		rectangle.setY(get_up() * view.get_scale());
		rectangle.setHeight(get_height() * view.get_scale());
		rectangle.setWidth(get_width() * view.get_scale());

	}
}
