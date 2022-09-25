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

	/**
	 * @return true if a player lost
	 */
	public boolean update(Court c, double deltaT) {
		double new_x = x + vx * deltaT;
		double new_y = y + vy * deltaT;
		if (new_y < 0) {
			new_y = -new_y;
			vy = Math.abs(vy);
		}
		if (new_y > c.getHeight()) {
			new_y = c.getHeight() - (new_y - c.getHeight());
			vy = -Math.abs(vy);
		}
		if (new_x < 0) {
			if (c.getPlayerA().collides(new_y)) {
				new_x = -new_x;
				vx = Math.abs(vx);
			} else {
				c.getScoreboard().addPoint(1);
				return true;
			}
		}
		if (new_x > c.getWidth()) {
			if (c.getPlayerB().collides(new_y)) {
				new_x = c.getWidth() - (new_x - c.getWidth());
				vx = -Math.abs(vx);
			} else {
				c.getScoreboard().addPoint(0);
				return true;
			}
		}
		x = new_x;
		y = new_y;
		return false;
	}

	public void render(GameView view, Court court) {
		circle.setRadius(size);
		circle.setFill(Color.BLACK);
		circle.setCenterX(x * view.getScale() + view.getXMargin());
		circle.setCenterY(y * view.getScale());
	}

	public void reset(Court c) {
		vx = 200;
		vy = 200;
		x = c.getWidth() / 2;
		y = c.getHeight() / 2;
	}
}
