package gamemodes;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

import javafx.scene.input.KeyCode;
import game_objects.Racket;
import game_objects.Ball;
import model.Court;

public class Ia implements Gamemode {
	private double difficulty;
	private boolean gauche;
	public Court court1;

	public Ia(double diff, boolean g) {
		difficulty = diff;
		gauche = g;
	}

	public void on_key_pressed(KeyCode key) {}
	public void on_key_released(KeyCode key) {}

	public boolean update(model.Court court, double dt) {
		if (gauche) {
			if (difficulty == 1) {
				if (court.get_ball().get_middle_y() > court.get_player_a().get_middle_y()) {
					court.get_player_a().set_direction(+0.5);
				} else {
					court.get_player_a().set_direction(-0.5);
				}
			}
			if (difficulty == 2) {
				if (court.get_ball().get_middle_y() > court.get_player_a().get_middle_y()) {
					court.get_player_a().set_direction(+0.75);
				} else {
					court.get_player_a().set_direction(-0.75);
				}
			}
			if (difficulty == 3) {
				if (court.get_ball().get_middle_y() > court.get_player_a().get_middle_y()) {
					court.get_player_a().set_direction(+1.0);
				} else {
					court.get_player_a().set_direction(-1.0);
				}
			}
			if (difficulty == 4) {
				court.get_player_a().set_direction((court.get_ball().get_middle_y() - court.get_player_a().get_middle_y()) / court.get_racket_speed() / 0.01);
			}
		} else {
			if (difficulty == 1) {
				if (court.get_ball().get_middle_y() > court.get_player_b().get_middle_y()) {
					court.get_player_b().set_direction(+0.5);
				} else {
					court.get_player_b().set_direction(-0.5);
				}
			}
			if (difficulty == 2) {
				if (court.get_ball().get_middle_y() > court.get_player_b().get_middle_y()) {
					court.get_player_b().set_direction(+0.75);
				} else {
					court.get_player_b().set_direction(-0.75);
				}
			}
			if (difficulty == 3) {
				if (court.get_ball().get_middle_y() > court.get_player_b().get_middle_y()) {
					court.get_player_b().set_direction(+1.0);
				} else {
					court.get_player_b().set_direction(-1.0);
				}
			}
			if (difficulty == 4) {
				court.get_player_b().set_direction((court.get_ball().get_middle_y() - court.get_player_b().get_middle_y()) / court.get_racket_speed() / 0.01);
			}
		}
		this.court1 = court;
		return false;
	}


	public void reset() {
		if (this.court1 != null) {
			court1.get_player_a().set_y(this.court1.get_height() / 2);
		}
	}

	public void render(gui.GameView view, Court court) {}
}
