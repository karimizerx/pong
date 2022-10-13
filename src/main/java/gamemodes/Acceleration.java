package gamemodes;

import javafx.scene.input.KeyCode;

public class Acceleration implements Gamemode {
    double accel_ball = 1.1;
    double accel_racket = 1;
    int v = 1;

    public Acceleration() {
    }

    public void reset() {
    }

    public void on_key_pressed(KeyCode key) {
    }

    public void on_key_released(KeyCode key) {
    }
    
    public void update(model.Court court, double deltaT) {
	if(v * court.getBall().get_vx() < 0){	    
	    court.getBall().accel_v(accel_ball);
	    court.addRacketSpeed(accel_racket);
	    v = court.getBall().get_vx();
	}
    }

    
    public void render(gui.GameView view, model.Court court) {
    }

    

}
