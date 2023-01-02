package gamemodes;

import javafx.scene.input.KeyCode;

public class InversionTouche implements Gamemode {
	private model.Court court1;
	private boolean inverted = false;
	private boolean gauche;

	public InversionTouche(boolean gauche) {
		this.gauche = gauche;
	}
	public String getName() {
		return "Invert keys (" + (gauche ? "gauche" : "droite") + ")";
	}

	public void on_key_pressed(KeyCode key) {}
	public void on_key_released(KeyCode key) {}
	public void on_ball_touched_racket(model.Court court, boolean left) {}
	public void on_ball_left_terrain(model.Court court, boolean left) {}

	public void update(model.Court court, double dt) {
		court1 = court;
		if (!inverted) {
			var player = gauche ? court.get_player_a() : court.get_player_b();
			KeyCode k = player.get_down_key();
			player.set_down_key(player.get_up_key());
			player.set_up_key(k);
			inverted = true;
		}
	}

	public void reset() {
		if (court1 == null) { return; }
		if (inverted) {
			var player = gauche ? court1.get_player_a() : court1.get_player_b();
			KeyCode k = player.get_down_key();
			player.set_down_key(player.get_up_key());
			player.set_up_key(k);
			inverted = false;
		}
	}
	public void no_render() {}
	public void update_render(gui.GameView view, model.Court court){}
	public void render() {}
}
