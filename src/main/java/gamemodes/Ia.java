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

	public void update(model.Court court, double deltaT) {
		if (gauche) {
			if (difficulty == 1) {
				if (court.getBall().getY() > court.getPlayerA().get_up())
					court.getPlayerA().setY(court.getPlayerA().get_up() + 0.2);
				else
					court.getPlayerA().setY(court.getPlayerA().get_up() - 0.2);
			}
			if (difficulty == 2) {
				if (court.getBall().getY() > court.getPlayerA().get_up())
					court.getPlayerA().setY(court.getPlayerA().get_up() + 0.5);
				else
					court.getPlayerA().setY(court.getPlayerA().get_up() - 0.5);
			}
			if (difficulty == 3) {
				if (court.getBall().getY() > court.getPlayerA().get_up())
					court.getPlayerA().setY(court.getPlayerA().get_up() + 2.5);
				else
					court.getPlayerA().setY(court.getPlayerA().get_up() - 2.5);
			}
			if (difficulty == 4) {
				court.getPlayerA().setY(court.getBall().getY() - court.getPlayerA().get_height() / 2);
			}
		} else {
			if (difficulty == 1) {
				if (court.getBall().getY() > court.getPlayerB().get_up())
					court.getPlayerB().setY(court.getPlayerB().get_up() + 0.2);
				else
					court.getPlayerB().setY(court.getPlayerB().get_up() - 0.2);
			}
			if (difficulty == 2) {
				if (court.getBall().getY() > court.getPlayerB().get_up())
					court.getPlayerB().setY(court.getPlayerB().get_up() + 0.5);
				else
					court.getPlayerB().setY(court.getPlayerB().get_up() - 0.5);
			}
			if (difficulty == 3) {
				if (court.getBall().getY() > court.getPlayerB().get_up())
					court.getPlayerB().setY(court.getPlayerB().get_up() + 2.5);
				else
					court.getPlayerB().setY(court.getPlayerB().get_up() - 2.5);;
			}
			if (difficulty == 4) {
				court.getPlayerB().setY(court.getBall().getY() - court.getPlayerB().get_height() / 2);
			}
		}
		this.court1 = court;
	}


	public void reset() {
		if (this.court1 != null) {
			court1.getPlayerA().setY((this.court1.getHeight() - court1.getPlayerA().get_height()) / 2);
		}
	}

	public void render(gui.GameView view, Court court) {}
}
