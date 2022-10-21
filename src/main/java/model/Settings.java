package model;

import javafx.scene.input.KeyCode;

public class Settings{
	public KeyCode left_up;
	public KeyCode left_down;
	public KeyCode right_up;
	public KeyCode right_down;
	public KeyCode stop;
	public KeyCode turbo;


	public Settings(){
		left_up = KeyCode.A;
		left_down = KeyCode.Q;
		right_up = KeyCode.UP;
		right_down = KeyCode.DOWN;
		stop = KeyCode.ESCAPE;
		turbo = KeyCode.SPACE;
	}
}
