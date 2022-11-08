package gamemodes;

import game_objects.Brique;
import gui.GameView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import model.Court;

public class CasseBrique implements Gamemode {
    int v = 1;
    Pane Root;

    public CasseBrique(Pane root) {
        Root = root;
    }

    @Override

    public void reset() {
    }

    @Override
    public void on_key_pressed(KeyCode key) {
    }

    @Override
    public void on_key_released(KeyCode key) {
    }

    @Override
    public void update(Court court, double dt) {
        if (v * court.get_ball().get_vx() < 0) {
            double x = Math.random() * (court.get_width() - 400) + 200;
            double y = Math.random() * (court.get_height() - 200) + 100;
            Brique brique = new Brique(court, x, y, Root);
            v = court.get_ball().get_vx();

        }
    }

    @Override
    public void render(GameView view, Court court) {
    }

}
