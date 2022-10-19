package gamemodes;

import javafx.scene.input.KeyCode;

public class RackWTF implements Gamemode {
	static float min_length = 1;
	static float max_length = 250;
	static int wtf = 50;

	public void reset(){}

	public void on_key_pressed(KeyCode key){
		if(key == KeyCode.R)
			reset();
	}

	public void on_key_released(KeyCode key){}
	
	private double random(double min, double max){
		return Math.random()*(max-min+1)+min;
	}

	public void update(model.Court court, double deltaT){
		double add_length_A;
		double add_length_B;

		if(court.getPlayerA().get_height() > min_length && court.getPlayerA().get_height() < max_length){
			add_length_A = random(-wtf, wtf);
		}else if (court.getPlayerA().get_height() < min_length){
			add_length_A = random(0, wtf);
		}else{
			add_length_A = random(-wtf, 0);
		}

		if(court.getPlayerB().get_height() > min_length && court.getPlayerB().get_height() < max_length){
			add_length_B = random(-wtf, wtf);
		}else if (court.getPlayerB().get_height() < min_length){
			add_length_B = random(0, wtf);
		}else{
			add_length_B = random(-wtf, 0);
		}

		court.getPlayerA().add_height(add_length_A);
		court.getPlayerA().add_y(add_length_A / 2);
		court.getPlayerB().add_height(add_length_B);
		court.getPlayerB().add_y(add_length_B / 2);



	}

	public void render(gui.GameView view, model.Court court){}

}
