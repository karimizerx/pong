package game_objects;

import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import model.Court;
import gui.GameView;
import game_objects.GameObject;

public class Ball implements GameObject {
	private Circle circle;

	private double x;
	private double y;
	private double vx;
	private double vy;
	private double size;
	private double ch;
	private boolean up;

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
	public double getY(){return y;}

	public boolean getUp(){return up;}
	/**
	 * @return true if a player lost
	 */
	public boolean update(Court c, double deltaT) {
		x += vx * deltaT;

	

		if(vy>0){up=true;}
		else{up=false;}
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
			ch+=0.3;
		} else if (this.collides(c.getPlayerB())) {
			// Likewise.
			x = (c.getPlayerB().get_left() - size) - (x - (c.getPlayerB().get_left() - size));
			vx = -Math.abs(vx);
			ch+=0.3;
		} else if (x < 0 || x > c.getWidth()) {
			if(x < 0)
				c.getScoreboard().addPoint(1);
				resetV();
			if(x > c.getWidth())
				c.getScoreboard().addPoint(0);
				resetV();
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
	public void resetV(){
		vx = 200;
		vy = 200;
		ch=0.3;
	}
}
