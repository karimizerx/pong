package gamemodes;

import javafx.scene.input.KeyCode;

public class RacketLength implements Gamemode{
	private double speed = 1;
	private double speed_A = speed;
	private double speed_B = speed;
	private double max_length = 200;
	private double min_length = 10;

	public RacketLength(){}

	public RacketLength(double speed){
		this.speed = speed;
		this.speed_A = speed;
		this.speed_B = speed;
	}

	public void reset(){
		
	}

	public void on_key_pressed(KeyCode key){}
	public void on_key_released(KeyCode key){}

	public void update(model.Court court, double deltaT){
		if(court.getPlayerA().get_height() > max_length || court.getPlayerA().get_height() < min_length)
			speed_A *= -1;

		if(court.getPlayerB().get_height() > max_length || court.getPlayerB().get_height() < min_length)
			speed_B *= -1;
	
		court.getPlayerA().add_height(speed_A);
		court.getPlayerA().add_y(-speed_A / 2);
		court.getPlayerB().add_height(speed_B);
		court.getPlayerB().add_y(-speed_B / 2);
	}

	public void render(gui.GameView view, model.Court court){}
}
