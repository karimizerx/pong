package gamemodes;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import model.Court;

public class Infini implements Gamemode{
	private int cntpasses = 0;
	private int paslvl;
	private model.Scoreboard internal_scoreboard;

	public Infini(Pane root) {
		internal_scoreboard = new model.Scoreboard(root, 1);
		this.paslvl = 3;
	}

	public boolean add_passe() {
		cntpasses+= 1;
		do_scores();
		return true;
	}

	public boolean do_scores() {
		int lvl = cntpasses/paslvl;
		if (cntpasses%paslvl!=0) {
			return false;
		}
		int[] s = {lvl};
		internal_scoreboard.set_scores(s);
		return true;
	}

	public void set_cntpasses(int s) {
		this.cntpasses=s;
		do_scores();
	}

	public void reset() {}

	public void on_key_pressed(KeyCode key) {}
	public void on_key_released(KeyCode key) {}
	public void on_ball_left_terrain(model.Court court, boolean left) {
		set_cntpasses(0);
	}
	public void update(model.Court court, double dt) {}

	public void on_ball_touched_racket(model.Court court, boolean left) {
		add_passe();
	}

	public void render(gui.GameView view, model.Court court) {
		internal_scoreboard.render(court.getColor(internal_scoreboard.get_color_val()));
	}
}
