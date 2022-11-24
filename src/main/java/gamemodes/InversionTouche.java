package gamemodes;

import javafx.scene.input.KeyCode;
import java.util.Random;
import model.Court;

public class InversionTouche implements Gamemode {
	boolean is_left = false;
	boolean desactive=false;
	Court court1;
	boolean deja_reset = false;
	public InversionTouche() {
		Random r = new Random();
		int n = r.nextInt(2);
		if(n==1){is_left = !is_left;}	
		
	}


	public void on_key_pressed(KeyCode key) {}
	public void on_key_released(KeyCode key) {}
	public void on_ball_touched_racket(model.Court court, boolean left) {}
	public void on_ball_left_terrain(model.Court court, boolean left) {	}
	
	

	public void update(model.Court court, double dt) {
		court1 = court;
		if(!desactive){
			if(is_left){
				KeyCode k = court.get_player_a().get_down_key();
				court.get_player_a().set_down_key(court.get_player_a().get_up_key());
				court.get_player_a().set_up_key(k);
			}
			else{
				KeyCode k = court.get_player_b().get_down_key();
				court.get_player_b().set_down_key(court.get_player_b().get_up_key());
				court.get_player_b().set_up_key(k);
			}
			desactive=!desactive;
		}
	
	}

	public void reset() {		
		if(court1 == null || deja_reset)return;
		if(is_left){
				KeyCode k = court1.get_player_a().get_down_key();
				court1.get_player_a().set_down_key(court1.get_player_a().get_up_key());
				court1.get_player_a().set_up_key(k);
			}
			else{
				KeyCode k = court1.get_player_b().get_down_key();
				court1.get_player_b().set_down_key(court1.get_player_b().get_up_key());
				court1.get_player_b().set_up_key(k);
			}
		deja_reset = true;
	}
	public void render(gui.GameView view, model.Court court) {}
}
