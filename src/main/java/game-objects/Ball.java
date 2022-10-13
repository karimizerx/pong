package game_objects;

import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import model.Court;
import gui.GameView;
import game_objects.GameObject;

import java.util.Random;

public class Ball implements GameObject {
	private Circle circle;

	private double x;
	private double y;
	private int vx;
	private int vy;
	private double size;

	public Ball(Pane root) {
		circle = new Circle();
		root.getChildren().add(circle);

		size = 10;
	}

	public double get_left() {
		return x - size;
	}
	public double get_right() {
		return x + size;
	}
	public double get_up() {
		return y - size;
	}
	public double get_down() {
		return y + size;
	}
	public double getY(){
		return y;
	}

	public void acc_x(double v) {
		vx += v;
	}
	public void acc_y(double v) {
		vy += v;
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
		if (this.collides(c.getPlayerA())) {
			// This formula mirrors x compared to the point where x would touch the bar.
			x = (c.getPlayerA().get_right() + size) - (x - (c.getPlayerA().get_right() + size));
			vx = Math.abs(vx);
		} else if (this.collides(c.getPlayerB())) {
			// Likewise.
			x = (c.getPlayerB().get_left() - size) - (x - (c.getPlayerB().get_left() - size));
			vx = -Math.abs(vx);
		} else if (x < 0 || x > c.getWidth()) {
			if(x < 0) {
				c.getScoreboard().addPoint(1);
			}
			if(x > c.getWidth()) {
				c.getScoreboard().addPoint(0);
			}
			return true;
		}
		return false;
	}

	public void render(GameView view, Court court) {
		circle.setRadius(size);
		circle.setFill(Color.BLACK);
		circle.setCenterX(x * view.getScale());
		circle.setCenterY(y * view.getScale());
	}

	private static int un_ou_moins_un(){
		Random r = new Random();
		int n = r.nextInt(2);
		if (n==0) { return -1; }
		return 1;
	}

	public void reset(Court c) {
		Random r = new Random();
		vx = (200 + (r.nextInt(30)*un_ou_moins_un()))*un_ou_moins_un();
		vy = (200 + (r.nextInt(30)*un_ou_moins_un()))*un_ou_moins_un();
		x = c.getWidth() / 2;
		y = c.getHeight() / 2;
	}
}
