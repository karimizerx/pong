package game_objects;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import model.Court;
import gui.GameView;
import game_objects.GameObject;

public class Brique extends GameObject {
    public Rectangle brique;
    Pane Root;

    public Brique(Court court, double x_pos, double y_pos, Pane root) {
        super(x_pos, y_pos, 10, 50);
        brique = new Rectangle(get_left(), get_up(), get_width(), get_height());
        int x = (int) (Math.random() * 256);
        int y = (int) (Math.random() * 256);
        int z = (int) (Math.random() * 256);

        brique.setFill(Color.rgb(x, y, z));
        Root = root;
        Root.getChildren().add(brique);
        
    }

    public boolean update(Court court, double dt) {
        if (this.collides(court.get_ball(), dt)) {
            court.get_ball().set_vel(-court.get_ball().get_dx(), court.get_ball().get_dy());
            brique.setVisible(false);
            return true;
        }
        return false;
    }

}
