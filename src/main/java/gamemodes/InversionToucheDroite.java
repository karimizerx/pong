package gamemodes;

import javafx.scene.input.KeyCode;
import java.util.Random;
import model.Court;

public class InversionToucheDroite implements Gamemode {
	boolean desactive=false;
	Court court1;
	boolean deja_reset = false;
	public InversionToucheDroite() {}

	public void on_key_pressed(KeyCode key) {}
	public void on_key_released(KeyCode key) {}
	public void on_ball_touched_racket(model.Court court, boolean left) {}
	public void on_ball_left_terrain(model.Court court, boolean left) {	}
	
	public void update(model.Court court, double dt) {
		court1 = court;
		if(!desactive){
			KeyCode k = court.get_player_b().get_down_key();
			court.get_player_b().set_down_key(court.get_player_b().get_up_key());
			court.get_player_b().set_up_key(k);
			desactive=true;
		}
	}

	public void reset() {
		if(court1 == null || deja_reset){deja_reset = false;return;}
		KeyCode k = court1.get_player_b().get_down_key();
		court1.get_player_b().set_down_key(court1.get_player_b().get_up_key());
		court1.get_player_b().set_up_key(k);
		deja_reset = true;
		desactive = false;
	}
	public void no_render() {}
	public void update_render(gui.GameView view, model.Court court){}
	public void render() {}
}

