package gamemodes;

import javafx.scene.input.KeyCode;

public interface Gamemode {
	public void reset();
	public void on_key_pressed(KeyCode key);
	public void on_key_released(KeyCode key);
	public void update(model.Court court, double deltaT);
	public void render(gui.GameView view, model.Court court);
}
