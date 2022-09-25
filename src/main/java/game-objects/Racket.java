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
	private double x;
	private double y;
	private double w;
	private double h;
	private Rectangle rectangle;
	private boolean is_left_racket;

	public Racket(Pane root, KeyCode up_key, KeyCode down_key, double w, double h, boolean is_left_racket) {
		this.up_key = up_key;
		this.down_key = down_key;
		direction = 0;
		y = 0;
		this.w = w;
		this.h = h;
		this.is_left_racket = is_left_racket;

		rectangle = new Rectangle();
		rectangle.setFill(Color.BLACK);
		root.getChildren().add(rectangle);
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

	public void update(Court court, double deltaT) {
		y += direction * deltaT * court.getRacketSpeed();
		if (y < 0) {
			y = 0;
		}
		if (y + h > court.getHeight()) {
			y = court.getHeight() - h;
		}
	}

	public void reset(Court court) {
		y = (court.getHeight() - h) / 2;
	}

	public void render(GameView view, Court court) {
		rectangle.setHeight(h * view.getScale());
		rectangle.setWidth(w * view.getScale());

		if (is_left_racket) {
			x = -w;
			rectangle.setX((x + view.getXMargin()) * view.getScale());
		} else {
			x = court.getWidth();
			rectangle.setX((x + view.getXMargin()) * view.getScale());
		}
		rectangle.setY(y * view.getScale());
	}
}
