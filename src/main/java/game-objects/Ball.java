package game_objects;

import java.util.Random;
import javafx.scene.shape.Circle;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import model.Court;
import gui.GameView;
import game_objects.GameObject;

public class Ball extends GameObject {
	private Circle circle;
	private Image skin;
	private double boost = 1200.0;
	private boolean boosted;

	private int colorval = 1;

	public Ball(Pane root, Image skin) {
		super(0, 0, 20, 20, skin);
		circle = new Circle();
		circle.setFill(new ImagePattern(skin));
		root.getChildren().add(circle);
		boosted = false;
	}

	public int get_color_val() {
		return this.colorval;
	}

	// Can't resize the ball. (For now ?)
	public void set_width(double v) {
	}

	public void set_height(double v) {
	}

	public void update(Court c, double dt) {
		super.update(c, dt);
		if (get_up() < 0) {
			if (get_dy() < 0) {
				scale_vel(1, -1);
			}
			change_y(2 * (0 - get_up()));
		} else if (get_down() > c.get_height()) {
			if (get_dy() > 0) {
				scale_vel(1, -1);
			}
			change_y(2 * (c.get_height() - get_down()));
		}
		if (this.collides(c.get_player_a(), dt)) {
			if (get_dx() < 0) {
				scale_vel(-1, 1);
				c.on_ball_touched_racket(true);
				if (boosted) {
					boosted = false;
					apply_force(-boost, 0);
				} else if (Math.abs(get_middle_y()-c.get_player_a().get_middle_y()) < 5) {
					boosted = true;
					apply_force(boost, 0);
				}
			}
			change_x(2 * (c.get_player_a().get_right() - get_left()));
			set_vel(get_dx(), 10*(get_middle_y()-c.get_player_a().get_middle_y()));
		} else if (this.collides(c.get_player_b(), dt)) {
			if (get_dx() > 0) {
				scale_vel(-1, 1);
				c.on_ball_touched_racket(false);
				if (boosted) {
					boosted = false;
					apply_force(boost, 0);
				} else if (Math.abs(get_middle_y()-c.get_player_b().get_middle_y()) < 5) {
					boosted = true;
					apply_force(-boost, 0);
				}
			}
			change_x(2 * (c.get_player_b().get_left() - get_right()));
			set_vel(get_dx(), 10*(get_middle_y()-c.get_player_b().get_middle_y()));
		} else if (get_right() < 0 || get_left() > c.get_width()) {
			if (get_right() < 0) {
				c.on_ball_left_terrain(true);
			}
			if (get_left() > c.get_width()) {
				c.on_ball_left_terrain(false);
			}
		}
	}

	public void render(GameView view, Court court, Color c) {
		circle.setRadius(get_width()); // Should be equal to get_height.
		circle.setCenterX(get_middle_x() * view.get_scale());
		circle.setCenterY(get_middle_y() * view.get_scale());
	}

	private static int un_ou_moins_un() {
		Random r = new Random();
		return r.nextInt(2) == 0 ? -1 : 1;
	}

	public void reset(Court c) {
		Random r = new Random();

		double vx = (200 + (r.nextInt(30) * un_ou_moins_un())) * un_ou_moins_un();
		double vy = (200 + (r.nextInt(30) * un_ou_moins_un())) * un_ou_moins_un();
		set_vel(vx, vy);
		set_x(c.get_width() / 2);
		set_y(c.get_height() / 2);
	}
}
