package gamemodes;

import java.util.LinkedList;

import game_objects.Brique;
import gui.GameView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import model.Court;
import javafx.scene.paint.Color;

public class CasseBrique implements Gamemode {
	double v = 1;
	Pane root;
	LinkedList<Brique> list;

	public CasseBrique(Pane root) {
		this.root = root;
		list = new LinkedList<Brique>();
	}

	public String getName() {
		return "Casse-Brique";
	}

	@Override
	public void reset() {
		for (Brique b : list) {
			b.brique.setVisible(false);
		}
		list.clear();
	}

	@Override
	public void on_key_pressed(KeyCode key) {}

	@Override
	public void on_key_released(KeyCode key) {}
	public void on_ball_touched_racket(model.Court court, boolean left) {
		double x = Math.random() * (court.get_width()  - 400) + 200;
		double y = Math.random() * (court.get_height() - 200) + 100;
		list.add(new Brique(court, x, y, root));
	}

	public void on_ball_left_terrain(model.Court court, boolean left) {}

	@Override
	public void update(Court court, double dt) {
		var to_remove = new java.util.LinkedList<Brique>();
		for(Brique b : list) {
			b.update(court, dt);
			if (b.getCasse()) { to_remove.add(b); }
		}
		for (Brique b : to_remove) {
			b.brique.setVisible(false);
			list.remove(b);
		}
	}

	public void render() {
		for (Brique b : list) {
			b.brique.setVisible(true);
		}
	}
	public void no_render() {
		for (Brique b : list) {
			b.brique.setVisible(false);
		}
	}
	@Override
	public void update_render(gui.GameView view, model.Court court) {}
}
