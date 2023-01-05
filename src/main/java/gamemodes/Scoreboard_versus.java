package gamemodes;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class Scoreboard_versus implements Gamemode {
	private model.Scoreboard internal_scoreboard;

	public Scoreboard_versus(Pane root) {
		internal_scoreboard = new model.Scoreboard(root, 2);
	}

	public String getName() {
		return "Scoreboard - Versus";
	}

	public void reset() {}

	public void on_key_pressed(KeyCode key) {}
	public void on_key_released(KeyCode key) {}
	public void on_ball_left_terrain(model.Court court, boolean left) {
		internal_scoreboard.add_point(left ? 1 : 0);
	}
	public void update(model.Court court, double dt) {}

	public void on_ball_touched_racket(model.Court court, boolean left) {}

	public void render() {
		internal_scoreboard.render();
	}
	public void no_render() {
		internal_scoreboard.no_render();
	}
	public void update_render(gui.GameView view, model.Court court) {
		internal_scoreboard.update_render();
	}
}
