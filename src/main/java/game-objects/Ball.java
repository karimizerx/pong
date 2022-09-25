package game_objects;

import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import model.Court;
import gui.GameView;

public class Ball {
	private Circle circle;

	private double x;
	private double y;
	private double vx;
	private double vy;
	private double size;

	public Ball(Pane root) {
		circle = new Circle();
		root.getChildren().add(circle);

		size = 10;
	}

	public double get_left_x() {
		return x - size;
	}
	public double get_right_x() {
		return x + size;
	}
	public double get_up_y() {
		return y - size;
	}
	public double get_down_y() {
		return y + size;
	}

	/**
	 * @return true if a player lost
	 */
	public boolean update(Court c, double deltaT) {
		x += vx * deltaT;
		y += vy * deltaT;
		if (y < 0) {
			y = -y;
			vy = Math.abs(vy);
		} else if (y > c.getHeight()) {
			y = c.getHeight() - (y - c.getHeight());
			vy = -Math.abs(vy);
		}
		if (c.getPlayerA().collides(this)) {
			// This formula mirrors x compared to the point where x would touch the bar.
			x = (c.getPlayerA().get_right_x() + size) - (x - (c.getPlayerA().get_right_x() + size));
			vx = Math.abs(vx);
		} else if (c.getPlayerB().collides(this)) {
			// Likewise.
			x = (c.getPlayerB().get_left_x() - size) - (x - (c.getPlayerB().get_left_x() - size));
			vx = -Math.abs(vx);
		} else if (x < 0 || x > c.getWidth()) {
			return true;
		}
		return false;
	}

	public void render(GameView view, Court court) {
		circle.setRadius(size);
		circle.setFill(Color.BLACK);
		circle.setCenterX((x + view.getXMargin()) * view.getScale());
		circle.setCenterY(y * view.getScale());
	}

	public void reset(Court c) {
		vx = 200;
		vy = 200;
		x = c.getWidth() / 2;
		y = c.getHeight() / 2;
	}
}
