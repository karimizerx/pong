package gamemodes;

import java.util.LinkedList;

import game_objects.Brique;
import gui.GameView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import model.Court;
import javafx.scene.paint.Color;

public class CasseBrique implements Gamemode {
    double v = 1;
    Pane Root;
    LinkedList<Brique> list;
    
    public CasseBrique(Pane root) {
        Root = root;
        list = new LinkedList<Brique>();
    }

    @Override

    public void reset() {
        for(Brique b : list){
            b.brique.setVisible(false);    
            list.remove(b);
            }
        }

    

    @Override
    public void on_key_pressed(KeyCode key) {
    }

    @Override
    public void on_key_released(KeyCode key) {
    }
    public void on_ball_touched_racket(model.Court court, boolean left){}

    public void on_ball_left_terrain(model.Court court, boolean left){}

    @Override
    public void update(Court court, double dt) {
        if(court.get_ball().get_left() < 0 
        || court.get_ball().get_right() > court.get_width()){
            reset();
        }
        
        if (v * court.get_ball().get_dx() < 0
        && (court.get_ball().get_right() < 200
            || court.get_ball().get_left() > court.get_width() - 200)) {

            double x = Math.random() * (court.get_width() - 400) + 200;
            double y = Math.random() * (court.get_height() - 200) + 100;
            list.add(new Brique(court, x, y, Root));
        }
        v = court.get_ball().get_dx();
        
        for(Brique b : list){
            
            b.update(court, dt);
            if(b.getCasse()) list.remove(b);
             
            }
        }
    

    @Override
    public void render(GameView view, Court court) {
    }

}
