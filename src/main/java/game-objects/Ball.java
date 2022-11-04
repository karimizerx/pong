package game_objects;

import java.util.Random;
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
	private int vx;
	private int vy;
	private double size;
	private int colorval = 1;

	public Ball(Pane root) {
		circle = new Circle();
		root.getChildren().add(circle);

		size = 10;
	}

	public int get_color_val() {
		return this.colorval;
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
	public double get_middle_x() {
		return x;
	}
	public double get_middle_y() {
		return y;
	}

	public void apply_force(double x, double y) {
		vx += x;
		vy += y;
	}

	public void scale_vel(double v) {
		vx *= v;
		vy *= v;
	}

	public int get_vx() {
		return vx;
	}
	public int get_vy() {
		return vy;
	}

	/**
	 * @return true if a player lost
	 */
	public boolean update(Court c, double dt) {
		x += vx * dt;
		y += vy * dt;
		if (y < 0) {
			y = -y;
			vy = Math.abs(vy);
		} else if (y > c.get_height()) {
			y = c.get_height() - (y - c.get_height());
			vy = -Math.abs(vy);
		}
		if (this.collides(c.get_player_a())) {
			// This formula mirrors x compared to the point where x would touch the bar.
			x = (c.get_player_a().get_right() + size) - (x - (c.get_player_a().get_right() + size));
			vx = Math.abs(vx);
			if (y < c.get_player_b().get_up() + 10) {
                                vy = -Math.abs(vy);
                        } else if (y > c.get_player_b().get_down() - 10) {
                                vy = Math.abs(vy);
                        }

		} else if (this.collides(c.get_player_b())) {
			// Likewise.
			x = (c.get_player_b().get_left() - size) - (x - (c.get_player_b().get_left() - size));
			//la balle a un rebond en fonction de l'endroit ou elle touche la raquette
			vx = -Math.abs(vx);
			if (y < c.get_player_b().get_up() + 10) {
				vy = -Math.abs(vy);
			} else if (y > c.get_player_b().get_down() - 10) {
				vy = Math.abs(vy);
			}
		} else if (x < 0 || x > c.get_width()) {
			if(x < 0) {
				c.get_scoreboard().add_point(1);
			}
			if(x > c.get_width()) {
				c.get_scoreboard().add_point(0);
			}
			return true;
		}
		return false;
	}

	public void render(GameView view, Court court, Color c) {
		circle.setRadius(size);
		circle.setFill(c);
		circle.setCenterX(x * view.get_scale());
		circle.setCenterY(y * view.get_scale());
	}

	private static int un_ou_moins_un() {
		Random r = new Random();
		return r.nextInt(2) == 0 ? -1 : 1;
	}

	public void reset(Court c) {
		Random r = new Random();
		vx = (200 + (r.nextInt(30) * un_ou_moins_un())) * un_ou_moins_un();
		vy = (200 + (r.nextInt(30) * un_ou_moins_un())) * un_ou_moins_un();
		x = c.get_width() / 2;
		y = c.get_height() / 2;
	}
}
