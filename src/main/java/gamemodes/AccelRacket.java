package gamemodes;

import javafx.scene.input.KeyCode;
import java.util.Random;
import model.Court;

public class AccelRacket implements Gamemode {
	Court court1;
	int mult;
	double vitesse_nv;
	public AccelRacket(int x) {
		this.mult=x;
	}


	public void on_key_pressed(KeyCode key) {}
	public void on_key_released(KeyCode key) {}
	public void on_ball_touched_racket(model.Court court, boolean left) {}
	public void on_ball_left_terrain(model.Court court, boolean left) {}

	boolean desactive=false;
	public void update(model.Court court, double dt) {
		if(!desactive){
			this.vitesse_nv=court.get_racket_speed()*mult;
            court.add_racket_speed(vitesse_nv);
			desactive=!desactive;
		}
		court1=court;
	}

	public void reset() {
		if(court1==null)return;
			court1.add_racket_speed(-vitesse_nv);
			desactive=false;
	}
	public void no_render() {}
	public void update_render(gui.GameView view, model.Court court){}
	public void render() {}
}
