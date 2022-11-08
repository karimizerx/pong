package game_objects;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import model.Court;
import gui.GameView;
import game_objects.GameObject;

public class Brique implements GameObject {

    Pane Root;

    public Brique(Court court, double x_pos, double y_pos, Pane root) {
        super();
        Rectangle brique = new Rectangle(x_pos, y_pos, 10, 50);
        Root = root;
        Root.getChildren().add(brique);
    }

    public void update(Court court, double dt) {
        if (this.collides(court.get_ball())) {
            court.get_ball().set_vx(-court.get_ball().get_vx());
            Root.getChildren().remove(this);
        }
    }

}
