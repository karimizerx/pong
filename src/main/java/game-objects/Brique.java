package game_objects;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import model.Court;

public class Brique extends GameObject {
	private Boolean casse;

	public Boolean getCasse() {
		return casse;
	}

	public Brique(Court court, double x_pos, double y_pos, Pane root) {
		super(x_pos, y_pos, 10, 50, root, Color.color(Math.random(), Math.random(), Math.random()));
		casse = false;
	}

	public void update(Court court, double dt) {
		if (this.collides(court.get_ball(), dt)) {
			court.get_ball().set_vel(-court.get_ball().get_dx(), court.get_ball().get_dy());
			hide(true);
			casse = true;
		}
	}
}
