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
		x += vx * deltaT;
		y += vy * deltaT;
		if (y < 0) {
			y = -y;
			vy = Math.abs(vy);
		} else if (y > c.getHeight()) {
			y = c.getHeight() - (y - c.getHeight());
			vy = -Math.abs(vy);
		}
		if (x < 0 && c.getPlayerA().collides(y)) {
			x = -x;
			vx = Math.abs(vx);
		} else if (x > c.getWidth() && c.getPlayerB().collides(y)) {
			x = c.getWidth() - (x - c.getWidth());
			vx = -Math.abs(vx);
		} else if (x < 0 || x > c.getWidth()) {
			return true;
		}
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
